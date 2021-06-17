package site.budanitskaya.chemistryquiz.fine.ui.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import site.budanitskaya.chemistryquiz.fine.MainApplication
import javax.inject.Inject

class TopicListViewModel @Inject constructor(val preferences: SharedPreferences) : ViewModel() {

/*    private var _numOfOpenLevels = MutableLiveData(1)
    val numOfOpenLevels: LiveData<Int>
        get() = _numOfOpenLevels*/


    var numOfOpenLevels =
        preferences
            .getInt("key_level", 1)


}