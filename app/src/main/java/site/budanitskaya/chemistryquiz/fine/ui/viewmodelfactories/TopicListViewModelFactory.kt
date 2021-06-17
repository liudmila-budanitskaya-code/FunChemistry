package site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TopicListViewModel

class TopicListViewModelFactory(val preferences: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopicListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopicListViewModel(preferences) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}