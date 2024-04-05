// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi;

import com.intellij.DynamicBundle;

public final class ZigZenBundle extends DynamicBundle {
  public static final ZigZenBundle IDE_UI_BUNDLE = new ZigZenBundle("messages.ZigIdeUIBundle");
  public static final ZigZenBundle ZIG_BUNDLE = new ZigZenBundle("messages.ZigBundle");
  public static final ZigZenBundle ZON_BUNDLE = new ZigZenBundle("messages.ZigZonBundle");

  private ZigZenBundle(String bundleName) {
      super(ZigZenBundle.class, bundleName);
  }
}
