package com.example.pinapp.ui.addPin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.pinapp.PinApplication
import com.example.pinapp.R
import com.example.pinapp.databinding.FragmentAddPinBinding
import com.example.pinapp.ui.base.BaseFragment
import com.example.pinapp.ui.dialog.DialogTypeEnum
import com.example.pinapp.ui.dialog.InfoDialog.Companion.openInfoDialog
import com.example.pinapp.ui.main.MainActivity

class AddPinFragment : BaseFragment() {

    private lateinit var binding: FragmentAddPinBinding

    companion object {
        const val MAX_REPEAT_DIGIT = 4
        const val MAX_CODE_LENGTH = 6
    }

    override val viewModel: AddPinViewModel by lazy {
        viewModelOf(
            AddPinViewModel::class
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_pin, container, false)
        PinApplication().appComponent.inject(this)
        binding = FragmentAddPinBinding.bind(view)

        observeAction()
        initView()
        return binding.root
    }

    private fun observeAction() {
        viewModel.action.observe(viewLifecycleOwner) { baseAction ->
            when (baseAction) {
                is AddPinViewModel.AddPinAction.ShowPinIsAddedDialog -> {
                    activity?.supportFragmentManager?.openInfoDialog(
                        dialogTypeEnum = DialogTypeEnum.SUCCESS,
                        message = getString(R.string.pin_added),
                        onClick = ::onBackPressed
                    )
                }
                is AddPinViewModel.AddPinAction.ShowInvalidNameDialog -> {
                    activity?.supportFragmentManager?.openInfoDialog(
                        dialogTypeEnum = DialogTypeEnum.ERROR,
                        message = getString(R.string.invalid_name),
                        onClick = null
                    )
                }
                else -> {

                }
            }

        }
    }

    private fun onBackPressed() {
        (activity as? MainActivity)?.onBackPressedDispatcher?.onBackPressed()
    }

    private fun initView() {

        binding.buttonGenerate.setOnClickListener {
            viewModel.generateAndInsertPin()
        }

        binding.nameEditText.addTextChangedListener {
            viewModel.changePinName(it.toString())
        }
    }

}
