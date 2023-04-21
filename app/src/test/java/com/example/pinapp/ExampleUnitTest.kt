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
        val numWithRepeats = 112112
        val numWithoutRepeats = 112233
        val digits = num.toString().toCharArray()

        // Check that the generated number has 6 digits
        assertEquals(6, digits.size)


        //check that the numWithRepeats contains more than 4 the same digits
        assertTrue(
            numWithRepeats.toString().groupBy { it }.any { it.value.size >= 4 }
        )

        //check that the numWithoutRepeats contains more than 4 the same digits
        assertFalse(
            numWithoutRepeats.toString().groupBy { it }.any { it.value.size >= 4 }
        )

        // Check that the generated number doesn't contain the same digit four times in a row
        assertFalse(
            num.toString().groupBy { it }.any { it.value.size >= 4 }
        )
    }
}