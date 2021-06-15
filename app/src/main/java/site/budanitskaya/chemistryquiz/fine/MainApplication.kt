package site.budanitskaya.chemistryquiz.fine

import android.app.Application
import android.content.Context
import site.budanitskaya.chemistryquiz.fine.ui.notifications.NotificationUtil

class MainApplication : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getApplication() = instance
    }

    override fun onCreate() {
        super.onCreate()
        NotificationUtil.createNotificationChannel(this)

    }
}