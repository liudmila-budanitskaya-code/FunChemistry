package site.budanitskaya.chemistryquiz.fine.dialogs

import android.app.AlertDialog
import site.budanitskaya.chemistryquiz.fine.models.Topic
import site.budanitskaya.chemistryquiz.fine.ui.gamelist.GameListFragment
import site.budanitskaya.chemistryquiz.fine.ui.topiclist.TopicListFragment

fun showAlertDialog(topic: Topic, fragment: TopicListFragment) {
    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
    alertDialog.setTitle("Select Option:")
    val items = arrayOf("Test Mode", "FlashCard Mode")
    val checkedItem = 1
    alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
        when (which) {
            0 -> {
                dialog.dismiss()

                fragment.navigateToTest(topic)
            }
            1 -> {
                dialog.dismiss()
                fragment.navigateToCards(topic)
            }
        }
    }
    val alert = alertDialog.create()
    alert.setCanceledOnTouchOutside(true)
    alert.show()
}

fun chooseDifficultyDialog(fragment: GameListFragment) {
    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
    alertDialog.setTitle("Chhose the number of questions")
    val items = arrayOf("3", "5", "7")
    val checkedItem = 1
    alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
        when (which) {
            0 -> {
                dialog.dismiss()

                fragment.navigateToChipsScreen(3)
            }
            1 -> {
                dialog.dismiss()
                fragment.navigateToChipsScreen(5)
            }
            3 -> {
                dialog.dismiss()
                fragment.navigateToChipsScreen(7)
            }

        }
    }
    val alert = alertDialog.create()
    alert.setCanceledOnTouchOutside(true)
    alert.show()
}