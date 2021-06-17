package site.budanitskaya.chemistryquiz.fine

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import site.budanitskaya.chemistryquiz.fine.di.ServiceLocator
import site.budanitskaya.chemistryquiz.fine.utils.NotificationUtil

@HiltAndroidApp
class MainApplication : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        lateinit var serviceLocator: ServiceLocator

        fun getApplication() = instance
    }

    override fun onCreate() {
        super.onCreate()
        NotificationUtil.createNotificationChannel(this)
        serviceLocator = ServiceLocator(applicationContext)
    }
}