package site.budanitskaya.chemistryquiz.fine.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import site.budanitskaya.chemistryquiz.fine.MainApplication

class TopicListViewModel : ViewModel() {

/*    private var _numOfOpenLevels = MutableLiveData(1)
    val numOfOpenLevels: LiveData<Int>
        get() = _numOfOpenLevels*/


    var numOfOpenLevels = PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext()).getInt("key_level", 1)



}