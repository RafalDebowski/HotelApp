package com.example.pinapp.ui.pins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinapp.PinApplication
import com.example.pinapp.R
import com.example.pinapp.databinding.FragmentPinsBinding
import com.example.pinapp.ui.FragmentEnum
import com.example.pinapp.ui.addPin.AddPinViewModel
import com.example.pinapp.ui.base.BaseFragment
import com.example.pinapp.ui.main.MainViewModel

class PinsFragment : BaseFragment() {

    lateinit var binding: FragmentPinsBinding

    private val pinAdapter = PinAdapter()

    override val viewModel: PinsViewModel by lazy {
        viewModelOf(
            PinsViewModel::class
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        PinApplication().appComponent.inject(this)
        val view = inflater.inflate(R.layout.fragment_pins, container, false)
        binding = FragmentPinsBinding.bind(view)

        observeData()
        initButtons()
        binding.recyclerView.adapter = pinAdapter

        viewModel.getPins()
        return binding.root
    }

    private fun observeData() {
        viewModel.pinList.observe(viewLifecycleOwner) {
            pinAdapter.setNewData(it)
        }
    }

    private fun initButtons() {
        binding.buttonAddPin.setOnClickListener {
            openFragment(FragmentEnum.ADD_PIN)
        }
    }
}