package site.budanitskaya.chemistryquiz.fine.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsFragment
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsViewModel
import site.budanitskaya.chemistryquiz.fine.ui.test.TestFragment
import site.budanitskaya.chemistryquiz.fine.ui.test.TestViewModel

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