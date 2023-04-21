package com.example.pinapp.ui.pins

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.ui.base.BaseViewModel
import com.example.pinapp.usecases.PinUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class PinsViewModel @Inject constructor(
    private val pinUseCase: PinUseCase
) : BaseViewModel() {

    val pinList = MutableLiveData<List<PinDomain>>()

    fun getPins() {
        val disposable = pinUseCase
            .getPins()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                pinList.postValue(it)
            }, {
                //todo show error
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
                           //todo
                }, { error ->
                    baseAction.value = Action.ShowInfoDialog(
                        message = error.message
                    )
                    Log.e("ERROR", error.message.toString())
                })

            addDisposable(disposable)
        }
    }

}