package site.budanitskaya.chemistryquiz.fine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import site.budanitskaya.chemistryquiz.fine.di.ServiceLocator
import site.budanitskaya.chemistryquiz.fine.firebasehelper.FirebaseAuthHelperImpl
import site.budanitskaya.chemistryquiz.fine.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    val firebaseAuthHelper = ServiceLocator(MainApplication.applicationContext()).provideFirebaseAuthHelper(this)

    val navigator = ServiceLocator(MainApplication.applicationContext()).provideNavigator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        actionBar?.show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.initialSetup()

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
        firebaseAuthHelper.signOut()
    }

    override fun onResume() {
        super.onResume()
        firebaseAuthHelper.updateView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigator.navigateToUpButton()
    }
}