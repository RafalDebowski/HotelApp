package com.example.pinapp

import android.app.Application
import com.example.pinapp.di.DaggerAppComponent

class PinApplication : Application() {

    val appComponent =  DaggerAppComponent.create()
}