package com.intellij.ide.plugins.cl;

import com.intellij.openapi.extensions.PluginId;

public class PluginClassLoader extends ClassLoader {
    public PluginId getPluginId() {
        return new PluginId();
    }
}
