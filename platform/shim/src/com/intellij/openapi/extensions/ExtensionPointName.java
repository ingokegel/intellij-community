package com.intellij.openapi.extensions;

public class ExtensionPointName<R> {
  public static <R> ExtensionPointName<R> create(String s) {
    return new ExtensionPointName<>();
  }

  public R[] getExtensions() {
    return (R[])new Object[0];
  }
}
