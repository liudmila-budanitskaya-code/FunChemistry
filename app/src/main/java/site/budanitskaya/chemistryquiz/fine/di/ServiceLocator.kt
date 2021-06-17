package site.budanitskaya.chemistryquiz.fine.di

import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.firebasehelper.FirebaseAuthHelper
import site.budanitskaya.chemistryquiz.fine.firebasehelper.FirebaseAuthHelperImpl
import site.budanitskaya.chemistryquiz.fine.navigator.AppNavigator
import site.budanitskaya.chemistryquiz.fine.navigator.AppNavigatorImpl

class ServiceLocator {

    fun provideNavigator(activity: MainActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }

    fun provideFirebaseAuthHelper(activity: MainActivity): FirebaseAuthHelper {
        return FirebaseAuthHelperImpl(activity)
    }
}