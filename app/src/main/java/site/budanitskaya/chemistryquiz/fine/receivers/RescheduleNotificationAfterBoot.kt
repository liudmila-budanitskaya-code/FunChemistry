package site.budanitskaya.chemistryquiz.fine.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.preference.PreferenceManager
import site.budanitskaya.chemistryquiz.fine.utils.NotificationUtil
import site.budanitskaya.chemistryquiz.fine.ui.fragments.NotificationsFragment


class RescheduleNotificationAfterBoot : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        /*Toast.makeText(context, "Booting Completed", Toast.LENGTH_LONG).show();*/
        Log.d("abracadabra", "onReceive: ")

        val notificationOn = PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(NotificationsFragment.NOTIFICATION_ON_PREFERENCE_KEY, false)

        if (notificationOn) {
            NotificationUtil.scheduleAlarmToTriggerNotification(context)
        }

    }
}
