package site.budanitskaya.chemistryquiz.fine.di

import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.ui.fragments.ChipsFragment
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.ChipsViewModel
import site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories.ChipsViewModelFactory

class ChipsLocator(val fragment: ChipsFragment) {

    fun provideViewModel(fragment: ChipsFragment): ChipsViewModel {
        return ViewModelProvider(
            fragment,
            ChipsViewModelFactory(ServiceLocator(MainApplication.applicationContext()).chipsDatasource)
        )
            .get(ChipsViewModel::class.java)
    }
}