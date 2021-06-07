package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChipsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChipsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChipsViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}