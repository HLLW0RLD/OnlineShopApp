package com.example.entrance.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.repository.ILocalRepository
import com.example.utils.extentions.Constants.LOG_IN_TAG
import com.example.utils.extentions.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogInViewModel : ViewModel(), KoinComponent {

    private val localService: ILocalRepository by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    val isUserSaved: MutableLiveData<Boolean> = MutableLiveData()
    val firstNameLiveData: MutableLiveData<String> = MutableLiveData()

    fun getUserByName(name: String?) {
        if (name != null) {
            disposable.add(
                localService
                    .getUserByName(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = {
                            firstNameLiveData.postValue(name)
                            isUserSaved.postValue(true)
                        },
                        onError = {
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