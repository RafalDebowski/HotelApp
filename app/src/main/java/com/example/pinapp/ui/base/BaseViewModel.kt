package com.example.pinapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pinapp.ui.dialog.DialogTypeEnum
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val baseAction = MutableLiveData<Action>()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    sealed class Action {
        data class ShowInfoDialog(
            val dialogTypeEnum: DialogTypeEnum = DialogTypeEnum.ERROR,
            val message: String?
        ) : Action()
    }
}