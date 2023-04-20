package com.example.pinapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.pinapp.PinApplication
import com.example.pinapp.R
import com.example.pinapp.databinding.ActivityMainBinding
import com.example.pinapp.ui.FragmentEnum

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        PinApplication().appComponent.inject(this)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
    }

    fun openFragment(fragmentEnum: FragmentEnum) {
        when (fragmentEnum) {
            FragmentEnum.ADD_PIN -> {
                navController.navigate(R.id.action_pinsFragment_to_addPinFragment)
            }
            FragmentEnum.PINS -> {
                navController.popBackStack()
            }
        }
    }
}