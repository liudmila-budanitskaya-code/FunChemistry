package site.budanitskaya.chemistryquiz.fine.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.res.ResourcesCompat
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.enums.CellState
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.MainViewModel

class CrosswordCellWatcher(val square : Square, val viwmodel: MainViewModel) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString()
                .toUpperCase() != square.value.toUpperCase() && s.toString() != "\n"
        ) {
            square.state = CellState.INCORRECT
            viwmodel.updateList(square)
        } else if (s.toString().equals(square.value, ignoreCase = true) && s.toString() != "\n"
        ) {
            square.state = CellState.CORRECT
            viwmodel.updateList(square)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().equals("", ignoreCase = true)
        ) {
            square.state = CellState.EMPTY
            viwmodel.updateList(square)
        }
    }
}