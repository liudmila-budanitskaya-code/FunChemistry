package site.budanitskaya.chemistryquiz.fine.dialogs

import android.app.AlertDialog
import site.budanitskaya.chemistryquiz.fine.domain.Topic
import site.budanitskaya.chemistryquiz.fine.ui.topiclist.TopicListFragment
import site.budanitskaya.chemistryquiz.fine.ui.topiclist.TopicListViewModel

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