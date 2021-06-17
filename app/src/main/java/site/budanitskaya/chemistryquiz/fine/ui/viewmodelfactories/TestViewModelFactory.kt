package site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TestViewModel

class TestViewModelFactory(val preferences: SharedPreferences, val questionRepository: QuestionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(preferences, questionRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}