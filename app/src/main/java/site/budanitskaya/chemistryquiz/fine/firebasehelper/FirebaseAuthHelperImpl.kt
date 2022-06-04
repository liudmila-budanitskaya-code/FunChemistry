package site.budanitskaya.chemistryquiz.fine.firebasehelper

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.ui.activities.LoginActivity

class FirebaseAuthHelperImpl(val activity: MainActivity) : FirebaseAuthHelper {
    val auth: FirebaseUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun updateView() {
        if (activity.intent != null) {
            createUI()
        } else {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
    }

    override fun createUI() {
        val list = auth?.providerData
        var providerData: String = ""
        list.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }
    }
}