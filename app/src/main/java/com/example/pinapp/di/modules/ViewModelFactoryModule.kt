package com.example.pinapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.pinapp.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}