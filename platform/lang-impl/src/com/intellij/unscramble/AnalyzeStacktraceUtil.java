// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.unscramble;

import com.intellij.execution.Executor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.impl.ConsoleViewUtil;
import com.intellij.execution.ui.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.extensions.ProjectExtensionPointName;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.concurrency.ThreadingAssertions;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static com.intellij.openapi.application.ex.ClipboardUtil.getTextInClipboard;


public final class AnalyzeStacktraceUtil {
  public static final ProjectExtensionPointName<Filter> EP_NAME = new ProjectExtensionPointName<>("com.intellij.analyzeStacktraceFilter");
  @ApiStatus.Experimental
  public static final ProjectExtensionPointName<StacktraceTabContentProvider> EP_CONTENT_PROVIDER =
    new ProjectExtensionPointName<>("com.intellij.analyzeStacktraceRunContentProvider");

  private AnalyzeStacktraceUtil() {
  }

  public static void printStacktrace(@NotNull ConsoleView consoleView, @NotNull String unscrambledTrace) {
    ThreadingAssertions.assertEventDispatchThread();
    consoleView.clear();
    consoleView.print(unscrambledTrace + "\n", ConsoleViewContentType.ERROR_OUTPUT);
    consoleView.scrollTo(0);
  }

  public interface ConsoleFactory {
    JComponent createConsoleComponent(ConsoleView consoleView, DefaultActionGroup toolbarActions);
  }

  public static void addConsole(Project project,
                                @Nullable ConsoleFactory consoleFactory,
                                final @NlsContexts.TabTitle String tabTitle,
                                String text) {
    addConsole(project, consoleFactory, tabTitle, text, null);
  }

  public static RunContentDescriptor addConsole(Project project,
                                                @Nullable ConsoleFactory consoleFactory,
                                                final @NlsContexts.TabTitle String tabTitle,
                                                String text,
                                                @Nullable Icon icon,
                                                Boolean withExecutor) {
    final TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
    builder.filters(EP_NAME.getExtensions(project));
    final ConsoleView consoleView = builder.getConsole();

    final DefaultActionGroup toolbarActions = new DefaultActionGroup();
    JComponent consoleComponent = consoleFactory != null
                                  ? consoleFactory.createConsoleComponent(consoleView, toolbarActions)
                                  : new MyConsolePanel(consoleView, toolbarActions);
    final RunContentDescriptor descriptor =
      new RunContentDescriptor(consoleView, null, consoleComponent, tabTitle, icon) {
        @Override
        public boolean isContentReuseProhibited() {
          return true;
        }
      };

    for (AnAction action : consoleView.createConsoleActions()) {
      toolbarActions.add(action);
    }
    final ConsoleViewImpl console = (ConsoleViewImpl)consoleView;
    ConsoleViewUtil.enableReplaceActionForConsoleViewEditor(console.getEditor());
    console.getEditor().getSettings().setCaretRowShown(true);
    toolbarActions.add(ActionManager.getInstance().getAction("AnalyzeStacktraceToolbar"));

    if (withExecutor) {
      final Executor executor = DefaultRunExecutor.getRunExecutorInstance();
      RunContentManager runContentManager = RunContentManager.getInstance(project);
      runContentManager.showRunContent(executor, descriptor);

      for (@NotNull StacktraceTabContentProvider stacktraceRunContentProvider : EP_CONTENT_PROVIDER.getExtensions(project)) {
        RunContentDescriptor contentDescriptor = stacktraceRunContentProvider.createRunTabDescriptor(project, text);
        if (contentDescriptor == null) continue;
        runContentManager.showRunContent(executor, contentDescriptor);
      }
    }
    consoleView.allowHeavyFilters();
    if (consoleFactory == null) {
      printStacktrace(consoleView, text);
    }
    return descriptor;
  }

  public static RunContentDescriptor addConsole(Project project,
                                                @Nullable ConsoleFactory consoleFactory,
                                                final @NlsContexts.TabTitle String tabTitle,
                                                String text,
                                                @Nullable Icon icon) {
    return addConsole(project, consoleFactory, tabTitle, text, icon, true);
  }

  private static final class MyConsolePanel extends JPanel {
    MyConsolePanel(ExecutionConsole consoleView, ActionGroup toolbarActions) {
      super(new BorderLayout());
      JPanel toolbarPanel = new JPanel(new BorderLayout());
      ActionToolbar toolbar =
        ActionManager.getInstance().createActionToolbar(ActionPlaces.ANALYZE_STACKTRACE_PANEL_TOOLBAR, toolbarActions, false);
      toolbar.setTargetComponent(consoleView.getComponent());
      toolbarPanel.add(toolbar.getComponent());
      add(toolbarPanel, BorderLayout.WEST);
      add(consoleView.getComponent(), BorderLayout.CENTER);
    }
  }

  public static StacktraceEditorPanel createEditorPanel(Project project, @NotNull Disposable parentDisposable) {
    EditorFactory editorFactory = EditorFactory.getInstance();
    Document document = editorFactory.createDocument("");
    Editor editor = editorFactory.createEditor(document, project);
    EditorSettings settings = editor.getSettings();
    settings.setFoldingOutlineShown(false);
    settings.setLineMarkerAreaShown(false);
    settings.setIndentGuidesShown(false);
    settings.setLineNumbersShown(false);
    settings.setRightMarginShown(false);

    StacktraceEditorPanel editorPanel = new StacktraceEditorPanel(project, editor);
    editorPanel.setPreferredSize(JBUI.size(600, 400));
    Disposer.register(parentDisposable, editorPanel);
    return editorPanel;
  }

  public static final class StacktraceEditorPanel extends JPanel implements UiDataProvider, Disposable {
    private final Project myProject;
    private final Editor myEditor;

    public StacktraceEditorPanel(Project project, Editor editor) {
      super(new BorderLayout());
      myProject = project;
      myEditor = editor;
      add(myEditor.getComponent());
    }

    @Override
    public void uiDataSnapshot(@NotNull DataSink sink) {
      sink.set(CommonDataKeys.EDITOR, myEditor);
    }

    public Editor getEditor() {
      return myEditor;
    }

    public void setText(final @NotNull String text) {
      Runnable runnable = () -> ApplicationManager.getApplication().runWriteAction(() -> {
        final Document document = myEditor.getDocument();
        document.replaceString(0, document.getTextLength(), StringUtil.convertLineSeparators(text));
      });
      CommandProcessor.getInstance().executeCommand(myProject, runnable, "", this);
    }

    public void pasteTextFromClipboard() {
      String text = getTextInClipboard();
      if (text != null) {
        setText(text);
      }
    }

    @Override
    public void dispose() {
      EditorFactory.getInstance().releaseEditor(myEditor);
    }

    public String getText() {
      return myEditor.getDocument().getText();
    }

    public JComponent getEditorComponent() {
      return myEditor.getContentComponent();
    }
  }
}
