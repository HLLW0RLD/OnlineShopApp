package com.example.home.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.repository.IRemoteRepository
import com.example.home.utils.FlashSalesRvState
import com.example.home.utils.LatestRvState
import com.example.utils.extentions.Constants.PAGE_ONE_TAG
import com.example.utils.subject.RxNavigationSubjects
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PageOneViewModel : ViewModel(), KoinComponent {

    private val remote: IRemoteRepository by inject()
    private val disposable: CompositeDisposable = CompositeDisposable()
    val latest: MutableLiveData<LatestRvState> = MutableLiveData()
    val flashSales: MutableLiveData<FlashSalesRvState> = MutableLiveData()

    fun getLatest() {
        latest.value = LatestRvState.Loading
        disposable.add(
            remote
                .getLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(PAGE_ONE_TAG, "getLatest() -> vm: $it")
                        RxNavigationSubjects.isAllItemsLoaded.onNext(true to false)
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
            remote
                .getFlashSale()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(PAGE_ONE_TAG, "getFlashSales() -> vm: $it")
                        RxNavigationSubjects.isAllItemsLoaded.onNext(false to true)
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