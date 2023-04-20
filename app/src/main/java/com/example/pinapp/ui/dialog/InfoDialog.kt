package com.example.pinapp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.pinapp.R
import com.example.pinapp.databinding.DialogErrorBinding

class InfoDialog : DialogFragment() {

    private lateinit var binding: DialogErrorBinding

    companion object {
        private const val INFO_DIALOG_TAG = "INFO_DIALOG"

        fun FragmentManager.openInfoDialog(
            onClick: (() -> Unit)?,
            dialogTypeEnum: DialogTypeEnum,
            message: String?
        ): InfoDialog {
            val infoDialog =
                InfoDialog()
            infoDialog.show(
                this,
                INFO_DIALOG_TAG
            )
            return infoDialog.apply {
                this.dialogTypeEnum = dialogTypeEnum
                this.message = message
                this.onClick = onClick
            }
        }
    }

    private lateinit var dialogTypeEnum: DialogTypeEnum
    private var message: String? = null
    private var onClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_error, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        initView()
        return binding.root
    }

    private fun initView() {
        when (dialogTypeEnum) {
            DialogTypeEnum.SUCCESS -> {
                binding.image.setBackgroundResource(R.drawable.ic_success)
            }
            DialogTypeEnum.ERROR -> {
                binding.image.setBackgroundResource(R.drawable.ic_error)
            }
        }

        binding.message.text = message ?: getString(R.string.error)

        binding.close.setOnClickListener {
            onClick?.let {
                it()
            }
            dismiss()
        }
    }
}