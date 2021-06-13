package site.budanitskaya.chemistryquiz.fine.ui.notifications

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.R
import java.util.*
import kotlin.random.Random


object NotificationUtil {
    private const val PENDING_INTENT_REQUEST_CODE: Int = 11 // use for cancelling pending intent
    private const val NOTIFICATION_ID: Int = 22
    private const val CHANNEL_ID: String = "com.betatech.learnspanish"

    /**
     * Called at app start, to create notification channel,
     * required for API 26+
     */
    fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * The broadcast receiver [RescheduleNotificationAfterBoot] for system boot,
     * is turned off by default in manifest, so that it doesn't turned on unnecessarily
     * it will be only be turned on, when notification is turned on in Settings
     * and disabled if user turn off notification
     */
    fun toggleNotificationRestartAfterBoot(context: Context, status: Boolean) {
        /*val receiver = ComponentName(context, RescheduleNotificationAfterBoot::class.java)

        context.packageManager.setComponentEnabledSetting(
            receiver,
            if (status) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )*/

    }

    /**
     * Schedule a new Alarm that will show a notification,
     * to remind user to practice, notification will repeat
     * each day.
     */
    fun scheduleAlarmToTriggerNotification(context: Context) {
        // cancel previous alarm if any
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

    /**
     * Cancel alarm which show notification, in case when user
     * turn off the notification, or is about to create
     * with new time
     */
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
            intent.putExtra(AlarmReceiver.NOTIFICATION_KEY, createNotification(context))
            PendingIntent.getBroadcast(context, PENDING_INTENT_REQUEST_CODE, intent, 0)
        }

    private fun createNotification(context: Context): Notification {
        val builder = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_message))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)

        return builder.build()
    }
}