package site.budanitskaya.chemistryquiz.fine.ui.topiclist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsViewModel

class TopicListViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopicListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopicListViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}