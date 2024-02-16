// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide;

import com.intellij.DynamicBundle;

public final class ZigIdeBundle extends DynamicBundle {
    public static final ZigIdeBundle UI_BUNDLE = new ZigIdeBundle("messages.ZigUIBundle");

    private ZigIdeBundle(String bundleName) {
        super(ZigIdeBundle.class, bundleName);
    }
}
