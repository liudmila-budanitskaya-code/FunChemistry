package site.budanitskaya.chemistryquiz.fine.navigator

import androidx.navigation.ActivityNavigator
import androidx.navigation.ui.AppBarConfiguration

interface AppNavigator {
    fun navigateToUpButton(): Boolean
    fun destChangeListen()
    fun superMethod()
}