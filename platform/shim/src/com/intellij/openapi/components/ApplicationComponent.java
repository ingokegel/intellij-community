package com.intellij.openapi.components;

public interface ApplicationComponent extends BaseComponent {
  /**
   * @deprecated Use {@link ApplicationComponent} directly
   */
  @Deprecated
  class Adapter implements ApplicationComponent {
  }
}
