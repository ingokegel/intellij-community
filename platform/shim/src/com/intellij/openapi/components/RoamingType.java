package com.intellij.openapi.components;

public enum RoamingType {
  /**
   * Stored only locally, not shared and not exportable (Export Settings dialog)
   */
  DISABLED,

  /**
   * Stored per operating system (Mac OS X, Linux, FreeBSD, Unix, Windows)
   */
  PER_OS,

  /**
   * Default, shared.
   */
  DEFAULT,

  /**
   * Use {@link #DEFAULT} instead
   */
  @Deprecated
  PER_USER,

  /**
   * Use {@link #PER_OS} instead
   */
  @Deprecated
  PER_PLATFORM,
}
