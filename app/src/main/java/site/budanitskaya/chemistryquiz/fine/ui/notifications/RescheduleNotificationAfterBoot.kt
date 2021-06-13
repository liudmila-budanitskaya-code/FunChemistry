package site.budanitskaya.chemistryquiz.fine.ui.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.preference.PreferenceManager

class RescheduleNotificationAfterBoot : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val notificationOn = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(NotificationsFragment.NOTIFICATION_ON_PREFERENCE_KEY, false)

            if (notificationOn) {
                NotificationUtil.scheduleAlarmToTriggerNotification(context)
            }
        }
    }
}