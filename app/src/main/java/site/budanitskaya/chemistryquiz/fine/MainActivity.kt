package site.budanitskaya.chemistryquiz.fine

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import site.budanitskaya.chemistryquiz.fine.ui.login.LoginActivity
import site.budanitskaya.chemistryquiz.fine.ui.notifications.AlarmReceiver

class MainActivity : AppCompatActivity() {
    private var mNotificationManager: NotificationManager? = null

    val auth = FirebaseAuth.getInstance().currentUser

    private lateinit var navView: BottomNavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy {
        this.findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        actionBar?.show()
        super.onCreate(savedInstanceState)
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        setContentView(R.layout.activity_main)
        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        val alarmUp = PendingIntent.getBroadcast(
            this, NOTIFICATION_ID,
            notifyIntent, PendingIntent.FLAG_NO_CREATE
        ) != null
        val notifyPendingIntent = PendingIntent.getBroadcast(
            this, NOTIFICATION_ID, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES
        val triggerTime = (SystemClock.elapsedRealtime())

        // If the Toggle is turned on, set the repeating alarm with
        // a 15 minute interval.
        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime, repeatInterval,
            notifyPendingIntent
        )
        navView = findViewById(R.id.nav_view)
        navView.itemRippleColor = getColorStateList(R.color.colorPrimary)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.quizListFragment, R.id.navigation_game, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when (destination.id) {
                R.id.quizListFragment  -> navView.visibility = View.VISIBLE
                R.id.navigation_game  -> navView.visibility = View.VISIBLE
                R.id.navigation_notifications  -> navView.visibility = View.VISIBLE
                R.id.questionFragment -> navView.visibility = View.GONE
                R.id.gameOverFragment -> navView.visibility = View.GONE
                R.id.chemChipsQuestionFragment -> navView.visibility = View.GONE
                R.id.crosswordFragment -> navView.visibility = View.GONE
                else -> navView.visibility = View.VISIBLE
            }
        }
        navView.setupWithNavController(navController)

        createNotificationChannel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.signout -> {
                signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
                Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onResume() {
        super.onResume()
        if (auth != null && intent != null) {
            createUI()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }

    fun createUI() {
        val list = auth?.providerData
        var providerData: String = ""
        list?.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    companion object {
        // Notification ID.
        private const val NOTIFICATION_ID = 0

        // Notification channel ID.
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    fun createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

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
}