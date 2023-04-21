package com.example.pinapp.ui.addPin

import androidx.lifecycle.MutableLiveData
import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.ui.addPin.AddPinFragment.Companion.MAX_CODE_LENGTH
import com.example.pinapp.ui.addPin.AddPinFragment.Companion.MAX_REPEAT_DIGIT
import com.example.pinapp.ui.base.BaseViewModel
import com.example.pinapp.usecases.PinUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPinViewModel @Inject constructor(
    private val pinUseCase: PinUseCase
) : BaseViewModel() {

    val action = MutableLiveData<AddPinAction>()
    private var pinName: String = ""

    fun changePinName(name: String) {
        pinName = name
    }

    fun generateAndInsertPin() {
        if (pinName.isNotEmpty()) {
            insertPinToDB(
                PinDomain(
                    name = pinName,
                    code = generateCode()
                )
            )
        } else {
            action.value = AddPinAction.ShowInvalidNameDialog
        }
    }

    fun generateCode(): Int {
        val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val digitsWithoutZero = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        digits.shuffle()
        digitsWithoutZero.shuffle()

        var generatedCode = 0

        for (i in 0 until MAX_CODE_LENGTH) {
            val digit : Int = if (i == 0) {
                digitsWithoutZero[i]
            } else {
                digits[i]
            }
            generatedCode *= 10
            generatedCode += digit
        }

        val hasRepeatingDigits = generatedCode.toString()
            .groupBy { it }
            .any {
                it.value.size >= MAX_REPEAT_DIGIT
            }

        if (hasRepeatingDigits) {
            return generateCode()
        }

        return generatedCode
    }

    private fun insertPinToDB(pin: PinDomain) {
        val disposable = pinUseCase
            .insertPin(pin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                action.value = AddPinAction.ShowPinIsAddedDialog
            }, { error ->
                baseAction.value = Action.ShowInfoDialog(
                    message = error.message.toString()
                )
            })
        addDisposable(disposable)
    }

    sealed class AddPinAction {
        object ShowPinIsAddedDialog : AddPinAction()
        object ShowInvalidNameDialog : AddPinAction()
    }
}