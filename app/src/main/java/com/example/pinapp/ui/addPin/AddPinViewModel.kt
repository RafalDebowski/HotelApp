package com.example.pinapp.ui.addPin

import androidx.lifecycle.MutableLiveData
import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.ui.base.BaseViewModel
import com.example.pinapp.ui.dialog.DialogTypeEnum
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
                    code = generatePin()
                )
            )
        } else {
            action.value = AddPinAction.ShowInvalidNameDialog
        }
    }

    fun generatePin(): Long {
        val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        digits.shuffle()

        var num = 0
        for (i in 0 until 6) {
            val digit = digits[i]
            num *= 10
            num += digit
        }

        val hasRepeatingDigits = num.toString().groupBy { it }.any { it.value.size >= 4 }

        if (hasRepeatingDigits) {
            return generatePin()
        }

        return num.toLong()
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

    sealed class AddPinAction() {
        object ShowPinIsAddedDialog : AddPinAction()
        object ShowInvalidNameDialog : AddPinAction()
    }
}