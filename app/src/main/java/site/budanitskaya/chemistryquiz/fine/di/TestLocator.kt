package site.budanitskaya.chemistryquiz.fine.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TestViewModel
import site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories.TestViewModelFactory

class TestLocator(val owner: ViewModelStoreOwner) {
    fun provideViewModel(owner: ViewModelStoreOwner): TestViewModel {
        return ViewModelProvider(
            owner,
            TestViewModelFactory(
                ServiceLocator(MainApplication.applicationContext()).preferences,
                ServiceLocator(MainApplication.applicationContext()).questionsRepository
            )
        )
            .get(TestViewModel::class.java)
    }
}