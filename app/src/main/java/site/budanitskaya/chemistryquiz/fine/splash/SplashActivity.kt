package site.budanitskaya.chemistryquiz.fine.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import site.budanitskaya.chemistryquiz.fine.R

class SplashActivity : AppCompatActivity() {


    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityScope.launch {
            delay(5000)

            val intent = Intent("site.budanitskaya.chemistryquiz.fine.LoginActivity")
            startActivity(intent)
            finish()
        }

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}