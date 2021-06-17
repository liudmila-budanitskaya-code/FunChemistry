package site.budanitskaya.chemistryquiz.fine.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import kotlinx.coroutines.*
import site.budanitskaya.chemistryquiz.fine.R
import java.util.*

class SoundService : Service() {

    private val job = SupervisorJob()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(job).launch {
            val mpthreesound = intent?.getIntExtra("soundCode", 1)
            val mPlayer2: MediaPlayer = MediaPlayer.create(this@SoundService, mpthreesound!!)
            mPlayer2.start()
            delay(2000)
            mPlayer2.stop()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}