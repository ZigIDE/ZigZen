// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.ide.ZigIdeBundle
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.observable.util.bindBooleanStorage
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.DialogPanel
import com.intellij.platform.DirectoryProjectGenerator
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import java.nio.file.InvalidPathException
import java.nio.file.Path
import javax.swing.JPanel

class ZigZenProjectSpecificSettingsStep<T>(
  projectGenerator: DirectoryProjectGenerator<T>,
  callback: AbstractNewProjectStep.AbstractCallback<T>
) : ProjectSettingsStepBase<T>(projectGenerator, callback), DumbAware {
  private val propertyGraph = PropertyGraph()
  private val projectName = propertyGraph.property("")
  private val projectLocation = propertyGraph.property("")
  private val locationHint = propertyGraph.property("").apply {
    dependsOn(projectName, ::updateHint)
    dependsOn(projectLocation, ::updateHint)
  }
  private val createRepository = propertyGraph.property(false)
    .bindBooleanStorage("ZigZen.NewProject.Git")

  private lateinit var projectNameField: JBTextField
  private lateinit var stepPanel: DialogPanel

  override fun createBasePanel(): JPanel {
    val possibleName = myProjectDirectory.get()
    projectName.set(possibleName.nameWithoutExtension)
    projectLocation.set(possibleName.parent)

    stepPanel = panel {
      row(ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.new.project.project.name.field")) {
        projectNameField = textField()
          .bindText(projectName)
          .align(Align.FILL)
          .component
      }
      row(ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.new.project.project.location.field")) {
        myLocationField = textFieldWithBrowseButton(fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor())
          .bindText(projectLocation)
          .align(Align.FILL)
          .component
      }
      row {
        comment("", maxLineLength = 60).bindText(locationHint)
      }
      row {
        checkBox(ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.new.project.project.git.field"))
          .bindSelected(createRepository)
      }
    }

    stepPanel.registerValidators(this) { validations ->
      val anyErrors = validations.entries.any { (key, value) -> key.isVisible && !value.okEnabled }
      myCreateButton.isEnabled = !anyErrors
    }
    myCreateButton.addActionListener { stepPanel.apply() }

    return stepPanel
  }

  private fun updateHint(): String =
    try {
      val projectPath = Path.of(projectLocation.get(), projectName.get())
      ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.new.project.project.location.hint", projectPath)
    }
    catch (e: InvalidPathException) {
      ""
    }
}
