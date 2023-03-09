package com.example.features.viewModel

import androidx.lifecycle.ViewModel
import com.example.app.domain.ILocalService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileViewModel : ViewModel(), KoinComponent {

    private val localService: ILocalService by inject()
//    private val disposable: CompositeDisposable = CompositeDisposable()

    fun logOut(name: String) {
        localService
            .delete(name)
    }
}