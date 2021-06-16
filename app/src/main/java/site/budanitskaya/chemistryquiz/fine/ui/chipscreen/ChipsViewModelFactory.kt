package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.datasource.ChipsDatasource

class ChipsViewModelFactory(private val chipsDatasource: ChipsDatasource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChipsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChipsViewModel(chipsDatasource) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}