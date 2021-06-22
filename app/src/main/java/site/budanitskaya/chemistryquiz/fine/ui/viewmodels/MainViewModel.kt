package site.budanitskaya.chemistryquiz.fine.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.utils.generateSquareListFromString
import site.budanitskaya.chemistryquiz.fine.utils.mainCrossword

class MainViewModel : ViewModel() {

    var _cellList = MutableLiveData(generateSquareListFromString(mainCrossword))
    val cellList: LiveData<List<Square>>
        get() = _cellList

    var header = "Chemical crossword"

}

