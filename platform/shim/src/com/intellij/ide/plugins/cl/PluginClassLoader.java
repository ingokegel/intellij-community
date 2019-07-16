package com.intellij.ide.plugins.cl;

import com.intellij.openapi.extensions.PluginId;

// core-impl
public class PluginClassLoader extends ClassLoader {
    public PluginId getPluginId() {
        return PluginId.getId("");
    }
}
