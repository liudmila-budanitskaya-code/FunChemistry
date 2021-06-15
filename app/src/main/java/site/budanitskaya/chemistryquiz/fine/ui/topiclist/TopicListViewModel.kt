package site.budanitskaya.chemistryquiz.fine.ui.topiclist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicListViewModel : ViewModel() {

/*    private var _numOfOpenLevels = MutableLiveData(1)
    val numOfOpenLevels: LiveData<Int>
        get() = _numOfOpenLevels*/

    var numOfOpenLevels = 6

}