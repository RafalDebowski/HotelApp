package com.example.pinapp

import com.example.pinapp.ui.addPin.AddPinViewModel
import com.example.pinapp.usecases.PinUseCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

class ExampleUnitTest {

    private lateinit var addPinViewModel: AddPinViewModel

    @Before
    fun init() {
        addPinViewModel = AddPinViewModel(
            Mockito.mock(PinUseCase::class.java)
        )
    }

    @Test
    fun testGenerateRandomInt() {
        val num = addPinViewModel.generateCode()
        val digits = num.toString().toCharArray()

        // Check that the generated number has 6 digits
        assertEquals(6, digits.size)

        // Check that the generated number doesn't contain the same digit four times in a row
        assertFalse(
            num.toString().groupBy { it }.any { it.value.size >= 4 }
        )
    }
}