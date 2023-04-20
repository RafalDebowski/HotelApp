package com.example.pinapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pinapp.di.factory.ViewModelFactory
import com.example.pinapp.ui.FragmentEnum
import com.example.pinapp.ui.base.BaseViewModel.Action.ShowInfoDialog
import com.example.pinapp.ui.dialog.InfoDialog.Companion.openInfoDialog
import com.example.pinapp.ui.main.MainActivity
import javax.inject.Inject
import kotlin.reflect.KClass

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    open val viewModel: BaseViewModel? by lazy {
        viewModelOf(
            BaseViewModel::class
        )
    }

    fun <T : ViewModel> viewModelOf(viewModelClass: KClass<T>): T =
        ViewModelProvider(this, viewModelFactory)[viewModelClass.java]


    fun openFragment(fragmentEnum: FragmentEnum) {
        (activity as? MainActivity)?.openFragment(
            fragmentEnum = fragmentEnum
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeAction()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeAction()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeAction() {
        viewModel?.baseAction?.observe(viewLifecycleOwner) { baseAction ->
            when (baseAction) {
                is ShowInfoDialog -> {
                    activity?.supportFragmentManager?.openInfoDialog(
                        dialogTypeEnum = baseAction.dialogTypeEnum,
                        message = baseAction.message,
                        onClick = null
                    )
                }
                else -> {

                }
            }

        }
    }
}