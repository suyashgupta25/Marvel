package com.marvel.common.domain

import io.reactivex.rxjava3.core.Observable

interface ObservableUseCase<Params, Result : Any> {

    fun observe(params: Params): Observable<Result>
}
