package com.intellij.openapi.extensions;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ExtensionPointName<T> {
  public static <T> ExtensionPointName<T> create(String s) {
    return new ExtensionPointName<>();
  }

  public T[] getExtensions() {
    return (T[])new Object[0];
  }

  @NotNull
  public List<T> getExtensionList() {
    return Collections.emptyList();
  }
}
