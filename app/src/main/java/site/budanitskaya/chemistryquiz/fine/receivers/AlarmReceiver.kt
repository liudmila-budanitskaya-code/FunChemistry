package site.budanitskaya.chemistryquiz.fine.receivers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.R
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var mNotificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        deliverNotification(context)
    }

    private fun deliverNotification(context: Context) {
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_school_24)
                .setContentTitle("It's time to practice chemistry!")
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)

        mNotificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        private const val NOTIFICATION_ID = 0
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }
}