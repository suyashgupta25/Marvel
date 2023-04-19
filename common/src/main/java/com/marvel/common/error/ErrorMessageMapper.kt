package com.marvel.common.error

import com.marvel.common.util.StringProvider
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorMessageMapper @Inject constructor(private val stringProvider: StringProvider) {

    fun apply(throwable: Throwable): String {
        Timber.e(throwable)
        return throwable.provideErrorMessage()
    }

    private fun Throwable.provideErrorMessage(): String = when {
        this.isInternetError() -> stringProvider.getInternetError()
        this is HttpException -> stringProvider.getHttpServerError()
        this is NoDataException -> stringProvider.getNoDataError()
        else -> stringProvider.getGeneralError()
    }

    private fun Throwable.isInternetError(): Boolean = this is UnknownHostException ||
            this is ConnectException ||
            this is SocketTimeoutException ||
            this is TimeoutException ||
            this is IOException
}
