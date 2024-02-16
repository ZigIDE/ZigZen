// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi;

import com.intellij.DynamicBundle;

public final class ZigZenBundle extends DynamicBundle {
    public static final ZigZenBundle UI_BUNDLE = new ZigZenBundle("messages.ZigUIBundle");

    private ZigZenBundle(String bundleName) {
        super(ZigZenBundle.class, bundleName);
    }
}
