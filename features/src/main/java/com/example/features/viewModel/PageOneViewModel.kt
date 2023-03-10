package com.example.features.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.domain.IRemoteService
import com.example.app.utils.Constants.PAGE_ONE_TAG
import com.example.app.utils.RxSubjects
import com.example.features.utils.FlashSalesRvState
import com.example.features.utils.LatestRvState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PageOneViewModel : ViewModel(), KoinComponent {

    private val remoteService: IRemoteService by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()
    val latest: MutableLiveData<LatestRvState> = MutableLiveData()
    val flashSales: MutableLiveData<FlashSalesRvState> = MutableLiveData()

    fun getLatest() {
        latest.value = LatestRvState.Loading
        disposable.add(
            remoteService
                .getLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(PAGE_ONE_TAG, "getLatest() -> vm: $it")
                        RxSubjects.isAllItemsLoaded.onNext(true to false)
                        latest.postValue(
                            LatestRvState.Success(it)
                        )
                    },
                    onError = {
                        Log.d(PAGE_ONE_TAG, "getLatest() -> vm: $it")
                    }
                )
        )
    }

    fun getFlashSales() {
        flashSales.value = FlashSalesRvState.Loading
        disposable.add(
            remoteService
                .getFlashSale()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(PAGE_ONE_TAG, "getFlashSales() -> vm: $it")
                        RxSubjects.isAllItemsLoaded.onNext(false to true)
                        flashSales.postValue(
                            FlashSalesRvState.Success(it)
                        )
                    },
                    onError = {
                        Log.d(PAGE_ONE_TAG, "getFlashSales() -> vm: $it")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}