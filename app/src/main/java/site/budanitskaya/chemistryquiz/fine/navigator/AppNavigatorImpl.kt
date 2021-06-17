package site.budanitskaya.chemistryquiz.fine.navigator

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.R

class AppNavigatorImpl(private val activity: MainActivity) : AppNavigator {

    private lateinit var navView: BottomNavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy {
        activity.findNavController(R.id.nav_host_fragment_activity_main)
    }



    override fun navigateToUpButton(): Boolean {
        val navController = activity.findNavController(R.id.nav_host_fragment_activity_main)
        return when (navController.currentDestination?.id) {
            R.id.chipsOverFragment -> {
                // custom behavior here
                navController.navigate(R.id.action_chipsOverFragment_to_navigation_game)
                true
            }
            R.id.gameOverFragment -> {
                // custom behavior here
                navController.navigate(R.id.action_gameOverFragment_to_quizListFragment)
                true
            }
            R.id.chemChipsQuestionFragment -> {
                // custom behavior here
                navController.navigate(R.id.action_chemChipsQuestionFragment_to_navigation_game)
                true
            }

            R.id.questionFragment -> {
                // custom behavior here
                navController.navigate(R.id.action_questionFragment_to_quizListFragment)
                true
            }


            else -> NavigationUI.navigateUp(navController, appBarConfiguration)
        }
    }

    override fun initialSetup() {
        navView = activity.findViewById(R.id.nav_view)
        navView.itemRippleColor = activity.getColorStateList(R.color.colorPrimary)

        val navController = activity.findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.quizListFragment, R.id.navigation_game, R.id.navigation_notifications
            )
        )
        activity.setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.quizListFragment -> navView.visibility = View.VISIBLE
                R.id.navigation_game -> navView.visibility = View.VISIBLE
                R.id.navigation_notifications -> navView.visibility = View.VISIBLE
                R.id.questionFragment -> navView.visibility = View.GONE
                R.id.gameOverFragment -> navView.visibility = View.GONE
                R.id.chemChipsQuestionFragment -> navView.visibility = View.GONE
                R.id.crosswordFragment -> navView.visibility = View.GONE
                R.id.chipsOverFragment -> navView.visibility = View.GONE
                else -> navView.visibility = View.VISIBLE
            }
        }
        navView.setupWithNavController(navController)
    }

}