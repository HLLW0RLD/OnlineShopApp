package com.example.features.viewModel

import androidx.lifecycle.ViewModel
import com.example.app.domain.ILocalService
import com.example.app.domain.IRemoteService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PageOneViewModel: ViewModel(), KoinComponent {

    private val remoteService: IRemoteService by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()


}