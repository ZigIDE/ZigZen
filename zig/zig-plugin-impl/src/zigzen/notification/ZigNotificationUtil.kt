// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.notification

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.util.NlsContexts.NotificationContent

object ZigNotificationUtil {
  private fun pluginNotifications() =
    NotificationGroupManager.getInstance().getNotificationGroup("zigzen.notification")

  fun Project.showBalloon(
    @NotificationContent content: String,
    notificationType: NotificationType,
    action: AnAction? = null
  ) {
    showBalloon("", content, notificationType, action)
  }

  private fun Project.showBalloon(
    @NlsContexts.NotificationTitle title: String,
    @NotificationContent content: String,
    notificationType: NotificationType,
    action: AnAction? = null,
    listener: NotificationListener? = null
  ) {
    val notification = pluginNotifications().createNotification(title, content, notificationType)

    if (listener != null)
      notification.setListener(listener)

    if (action != null)
      notification.addAction(action)

    Notifications.Bus.notify(notification, this)
  }
}
