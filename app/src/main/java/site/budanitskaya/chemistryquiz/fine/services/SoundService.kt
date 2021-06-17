package site.budanitskaya.chemistryquiz.fine.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*


@AndroidEntryPoint
class SoundService : Service() {

    private val job = SupervisorJob()
    lateinit var player: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(job).launch {
            val sound = intent?.getIntExtra("soundCode", 1)
            player = MediaPlayer.create(this@SoundService, sound!!)
            player.start()
            delay(2000)
            player.stop()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        try {
            if (player.isPlaying) player.stop()
            player.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}