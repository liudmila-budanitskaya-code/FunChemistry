package site.budanitskaya.chemistryquiz.fine.firebasehelper

import android.content.Intent
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.ui.login.LoginActivity

class FirebaseAuthHelperImpl(val activity: MainActivity) : FirebaseAuthHelper {
    val auth = FirebaseAuth.getInstance().currentUser
    override fun signOut() {
        AuthUI.getInstance()
            .signOut(activity)
            .addOnCompleteListener {
                val intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
                Toast.makeText(activity, "Successfully Log Out", Toast.LENGTH_SHORT).show()
            }
    }

    override fun updateView() {
        if (auth != null && activity.intent != null) {
            createUI()
        } else {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
    }

    override fun createUI() {
        val list = auth?.providerData
        var providerData: String = ""
        list?.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }
    }
}