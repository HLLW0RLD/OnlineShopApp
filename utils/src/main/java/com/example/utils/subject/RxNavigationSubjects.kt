package com.example.utils.subject

import io.reactivex.rxjava3.subjects.ReplaySubject

object RxNavigationSubjects {

    var openSignInPage: ReplaySubject<String?> = ReplaySubject.create()
    var openLogInPage: ReplaySubject<String?> = ReplaySubject.create()
    var openMainPage: ReplaySubject<String> = ReplaySubject.create()
    var openProfilePage: ReplaySubject<String> = ReplaySubject.create()
    var isAllItemsLoaded: ReplaySubject<Pair<Boolean, Boolean>> = ReplaySubject.create()

}