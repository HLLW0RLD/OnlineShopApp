package com.example.entrance.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.repository.ILocalRepository
import com.example.utils.extentions.Constants.SIGN_IN_TAG
import com.example.utils.extentions.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInViewModel : ViewModel(), KoinComponent {

    private val localService: ILocalRepository by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()

    val isUserSaved: MutableLiveData<Boolean> = MutableLiveData()
    val firstNameLiveData: MutableLiveData<String> = MutableLiveData()
    val lastNameLiveData: MutableLiveData<String> = MutableLiveData()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()

    fun saveUserData(firstName: String?, lastName: String?, email: String?) {
        if (firstName != null && lastName != null && email != null) {
            disposable
                .add(Single
                    .just(String)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                        onSuccess = {
                            localService.insert(
                                firstName,
                                lastName,
                                email
                            )
                            firstNameLiveData.postValue(firstName)
                            lastNameLiveData.postValue(lastName)
                            emailLiveData.postValue(email)

                            Log.d(
                                SIGN_IN_TAG,
                                "saveUserData() Fields:\nfirst name: $firstName \nlast name: $lastName \nemail: $email"
                            )
                        }
                    )
                )
        } else {
            Log.d(
                SIGN_IN_TAG,
                "One of fields is null:\nfirst name: $firstName \nlast name: $lastName \nemail: $email"
            )
        }
    }

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
                            Log.d(SIGN_IN_TAG, "getUserByName() user already exist")
                        },
                        onError = {
                            isUserSaved.postValue(false)
                            Log.d(SIGN_IN_TAG, "getUserByName() user not exist")
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