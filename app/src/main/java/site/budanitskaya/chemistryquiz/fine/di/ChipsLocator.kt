package site.budanitskaya.chemistryquiz.fine.di

import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsFragment
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsViewModel

class ChipsLocator(val fragment: ChipsFragment) {

    fun provideViewModel(fragment: ChipsFragment): ChipsViewModel {
        return ViewModelProvider(
            fragment,
            ChipsViewModelFactory(ServiceLocator(MainApplication.applicationContext()).chipsDatasource)
        )
            .get(ChipsViewModel::class.java)
    }
}