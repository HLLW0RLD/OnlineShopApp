package com.example.features.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.domain.ILocalService
import com.example.app.utils.Constants.LOG_IN_TAG
import com.example.app.utils.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogInViewModel : ViewModel(), KoinComponent {

    val isUserSaved: MutableLiveData<Boolean> = MutableLiveData()
    private val localService: ILocalService by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getUserByName(name: String?) {
        if (name != null) {
            disposable.add(
                localService
                    .getUserByName(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = {
                            isUserSaved.postValue(true)
                        },
                        onError = {
                            Helper.toastShort("User with this name not found")
                            Log.d(LOG_IN_TAG, "getUserByName() e -> $it")
                        }
                    )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}