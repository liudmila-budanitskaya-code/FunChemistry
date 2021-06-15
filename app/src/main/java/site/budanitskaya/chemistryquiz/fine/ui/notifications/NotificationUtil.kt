package site.budanitskaya.chemistryquiz.fine.ui.notifications

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.R
import java.util.*
import kotlin.random.Random

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

        // Create a notification manager object.
        mNotificationManager =
            context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.

        // Create the NotificationChannel with all the parameters.
        val notificationChannel = NotificationChannel(
            PRIMARY_CHANNEL_ID,
            "Stand up notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Notifies every 15 minutes to " +
                "stand up and walk"
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


/*

object NotificationUtil {
    private const val PENDING_INTENT_REQUEST_CODE: Int = 15 // use for cancelling pending intent
    private const val NOTIFICATION_ID: Int = 30
    private const val CHANNEL_ID: String = "site"

    fun createNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
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
        cancelAlarmToTriggerNotification(context)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = getNotifyTimeFromPreference(context)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            createPendingIntentForAlarm(context)
        )
    }
    fun cancelAlarmToTriggerNotification(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(createPendingIntentForAlarm(context))
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

    private fun createPendingIntentForAlarm(context: Context): PendingIntent =
        Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
            intent.putExtra(AlarmReceiver.NOTIFICATION_ID_KEY, NOTIFICATION_ID)
            PendingIntent.getBroadcast(context, PENDING_INTENT_REQUEST_CODE, intent, 0)
        }
}*/


