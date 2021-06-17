package site.budanitskaya.chemistryquiz.fine.firebasehelper

import site.budanitskaya.chemistryquiz.fine.MainActivity

interface FirebaseAuthHelper{
    fun signOut()
    fun updateView()
    fun createUI()
}