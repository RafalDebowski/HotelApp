package com.example.pinapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.pinapp.di.annotation.ViewModelKey
import com.example.pinapp.ui.addPin.AddPinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddPinViewModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddPinViewModel::class)
    abstract fun bindPinViewModel(viewModel: AddPinViewModel): ViewModel
}