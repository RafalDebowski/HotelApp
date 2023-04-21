package com.example.pinapp.ui.pins

import androidx.lifecycle.MutableLiveData
import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.ui.base.BaseViewModel
import com.example.pinapp.usecases.PinUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PinsViewModel @Inject constructor(
    private val pinUseCase: PinUseCase
) : BaseViewModel() {

    val pinList = MutableLiveData<MutableList<PinDomain>>()

    fun getPins() {
        val disposable = pinUseCase
            .getPins()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                pinList.postValue(it.toMutableList())
            }, { error ->
                baseAction.value = Action.ShowInfoDialog(
                    message = error.message
                )
            })

        addDisposable(disposable)
    }

    fun deletePinById(pin: PinDomain) {
        pin.id?.let {
            val disposable = pinUseCase
                .deletePinById(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    updateLocalList(pin)
                }, { error ->
                    baseAction.value = Action.ShowInfoDialog(
                        message = error.message
                    )
                })

            addDisposable(disposable)
        }
    }

    private fun updateLocalList(pin: PinDomain) {
        pinList.value?.remove(pin)
        pinList.postValue(pinList.value)
    }
}