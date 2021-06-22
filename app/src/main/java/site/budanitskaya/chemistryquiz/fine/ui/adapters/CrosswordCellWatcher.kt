package site.budanitskaya.chemistryquiz.fine.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.res.ResourcesCompat
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.models.Square

class CrosswordCellWatcher(val view: View, val square : Square) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString()
                .toUpperCase() != square.value.toUpperCase() && s.toString() != "\n"
        ) {
            view.background = ResourcesCompat.getDrawable(
                view.resources,
                R.color.pink,
                null
            )
        } else if (s.toString().equals(square.value, ignoreCase = true) && s.toString() != "\n"
        ) {
            view.background = ResourcesCompat.getDrawable(
                view.resources,
                R.color.light_green,
                null
            )
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().equals("", ignoreCase = true)
        ) {
            view.background = ResourcesCompat.getDrawable(
                view.resources,
                R.drawable.crossword_square,
                null
            )
        }
    }
}