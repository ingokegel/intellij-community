package com.intellij.notification;

import com.intellij.openapi.extensions.PluginId;

public class NotificationGroup {
  public static NotificationGroup logOnlyGroup(String s) {
    return new NotificationGroup();
  }

  public static NotificationGroup logOnlyGroup(String s, PluginId id) {
    return new NotificationGroup();
  }

  public Notification createNotification(String message, NotificationType information) {
    return new Notification();
  }
}
