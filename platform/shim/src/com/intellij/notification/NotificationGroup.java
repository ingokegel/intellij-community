package com.intellij.notification;

public class NotificationGroup {
  public static NotificationGroup logOnlyGroup(String s) {
    return new NotificationGroup();
  }

  public Notification createNotification(String message, NotificationType information) {
    return new Notification();
  }
}
