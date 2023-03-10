package com.example.app.utils

import io.reactivex.rxjava3.subjects.ReplaySubject

object RxSubjects {

    var nameData: ReplaySubject<String> = ReplaySubject.create()
    var isAllItemsLoaded: ReplaySubject<Pair<Boolean, Boolean>> = ReplaySubject.create()

}