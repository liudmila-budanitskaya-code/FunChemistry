package site.budanitskaya.chemistryquiz.fine.dialogs

import android.app.AlertDialog
import android.view.View
import site.budanitskaya.chemistryquiz.fine.models.Topic
import site.budanitskaya.chemistryquiz.fine.ui.fragments.GameListFragment
import site.budanitskaya.chemistryquiz.fine.ui.fragments.TopicListFragment

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
    alertDialog.setTitle("Choose the number of questions")
    val items = arrayOf("3", "5", "7")
    val checkedItem = 1
    alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
        when (which) {
            0 -> {
                dialog.dismiss()
                fragment.navigateToChipsScreen(fragment.requireView(), 3)
            }
            1 -> {
                dialog.dismiss()
                fragment.navigateToChipsScreen(fragment.requireView(),5)
            }
            3 -> {
                dialog.dismiss()
                fragment.navigateToChipsScreen(fragment.requireView(),7)
            }

        }
    }
    val alert = alertDialog.create()
    alert.setCanceledOnTouchOutside(true)
    alert.show()
}