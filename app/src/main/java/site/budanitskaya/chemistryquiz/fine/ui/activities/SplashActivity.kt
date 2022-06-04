package site.budanitskaya.chemistryquiz.fine.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import site.budanitskaya.chemistryquiz.fine.R


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val splashScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashScope.launch {
            delay(6480)
            val intent = Intent("site.budanitskaya.chemistryquiz.fine.ui.activities.LoginActivity")
            startActivity(intent)
            finish()
        }
    }
}