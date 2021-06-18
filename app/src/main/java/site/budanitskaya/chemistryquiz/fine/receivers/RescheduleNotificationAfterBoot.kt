package site.budanitskaya.chemistryquiz.fine.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.chemistryquiz.fine.utils.NotificationUtil
import site.budanitskaya.chemistryquiz.fine.ui.fragments.NotificationsFragment

@AndroidEntryPoint
class RescheduleNotificationAfterBoot : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {



        val notificationOn = PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(NotificationsFragment.NOTIFICATION_ON_PREFERENCE_KEY, false)

        if (notificationOn) {
            NotificationUtil.scheduleAlarmToTriggerNotification(context)
        }
    }
}
