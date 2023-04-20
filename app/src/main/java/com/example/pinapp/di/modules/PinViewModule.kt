package com.example.pinapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.pinapp.di.annotation.ViewModelKey
import com.example.pinapp.ui.pins.PinsFragment
import com.example.pinapp.ui.pins.PinsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        PinModule::class
    ]
)
abstract class PinViewModule {

    @Binds
    @IntoMap
    @ViewModelKey(PinsViewModel::class)
    abstract fun bindPinViewModel(viewModel: PinsViewModel): ViewModel

}