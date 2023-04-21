package com.example.pinapp.di

import com.example.pinapp.di.modules.AddPinViewModule
import com.example.pinapp.ui.main.MainActivity
import com.example.pinapp.di.modules.DatabaseModule
import com.example.pinapp.di.modules.PinViewModule
import com.example.pinapp.di.modules.ViewModelFactoryModule
import com.example.pinapp.ui.addPin.AddPinFragment
import com.example.pinapp.ui.pins.PinsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        ViewModelFactoryModule::class,
        PinViewModule::class,
        AddPinViewModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: PinsFragment)

    fun inject(fragment: AddPinFragment)

}