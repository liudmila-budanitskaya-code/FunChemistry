package site.budanitskaya.chemistryquiz.fine.ui.notifications

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import java.util.*

object NotificationUtil {

    private var mNotificationManager: NotificationManager? = null
    private lateinit var alarmIntent: PendingIntent
    private lateinit var alarmMgr: AlarmManager

    fun cancelNotification() {
        if (this::alarmMgr.isInitialized && this::alarmIntent.isInitialized) {
            alarmMgr.cancel(alarmIntent)
        }
    }

    fun createNotificationChannel(context: Context) {

        mNotificationManager =
            context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            PRIMARY_CHANNEL_ID,
            "Stand up notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        with(notificationChannel){
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "Notifies every 15 minutes to " +
                    "stand up and walk"
        }
        mNotificationManager!!.createNotificationChannel(notificationChannel)
    }

    fun toggleNotificationRestartAfterBoot(context: Context, status: Boolean) {
        val receiver = ComponentName(context, RescheduleNotificationAfterBoot::class.java)

        context.packageManager.setComponentEnabledSetting(
            receiver,
            if (status) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

    }

    fun scheduleAlarmToTriggerNotification(context: Context) {

        cancelNotification()
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        alarmMgr = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = getNotifyTimeFromPreference(context)

        alarmMgr.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000 * 60 * 20,
            alarmIntent
        )
    }

    private fun getNotifyTimeFromPreference(context: Context): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        val scheduleTime: String = PreferenceManager.getDefaultSharedPreferences(context).getString(
            NotificationsFragment.NOTIFICATION_TIME_PREFERENCE_KEY,
            NotificationsFragment.DEFAULT_NOTIFICATION_TIME
        ) ?: NotificationsFragment.DEFAULT_NOTIFICATION_TIME
        val (hour, minute) = scheduleTime.split(":")
        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        calendar.set(Calendar.MINUTE, minute.toInt())
        return calendar
    }

    const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
}