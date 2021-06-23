package site.budanitskaya.chemistryquiz.fine.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import site.budanitskaya.chemistryquiz.fine.constants.LOG_TAG
import site.budanitskaya.chemistryquiz.fine.enums.CellState
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.utils.generateSquareListFromString
import site.budanitskaya.chemistryquiz.fine.utils.mainCrossword

class MainViewModel : ViewModel() {

    var initList = generateSquareListFromString(mainCrossword)

    var _cellList = MutableLiveData(initList)
    val cellList: LiveData<MutableList<Square>>
        get() = _cellList

    var header = "Chemical crossword"

    fun updateList(square: Square) {
        // надо сконструировать новое livedata

       var x = listOf<Square>(Square(1, "2", "3", CellState.INCORRECT),
            Square(2, "3", "4", CellState.EMPTY))
        var temp = listOf<Square>(Square(1, "2", "3", CellState.INCORRECT),
            Square(2, "3", "4", CellState.EMPTY))
/*        initList.find { it.id == square.id }?.state = square.state*/
 /*      x?.find { it.id == 2 }?.state = CellState.EMPTY */
        val sq = Square(2, "3", "4", CellState.CORRECT)
        x.find { it.id == sq.id }?.state = sq.state





        Log.d(LOG_TAG, "${x == temp}")


    }
}

fun MutableList<Square>.replaceWith(square: Square): MutableList<Square> {
    val list = mutableListOf<Square>()
    this.find {
        it.id == square.id
    }
    return list
}

