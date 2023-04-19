package com.marvel.common.util

import com.marvel.common.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * We can use a utility library for e.g. Applanga which can prevent the values being added to
 * string.xml and that way we can use this string provider wrapper all around the app.
 *
 * Not a ideal method to store the public and private key actually.
 */
@Singleton
class StringProvider @Inject constructor() {

    //Home page toolbar title
    fun getHomeScreenTitle() = "Marvel"

    //Character details
    fun getLastUpdatedDateSuffix() = "Last updated:"

    //api key of marvel
    fun getMarvelPublicApiKey() = BuildConfig.MARVEL_PUBLIC_KEY
    fun getMarvelPrivateApiKey() = BuildConfig.MARVEL_PRIVATE_KEY

    //current timestamp
    fun getCurrentTimestamp() = System.currentTimeMillis().toString()

    //Error
    fun getHttpServerError() = "Server is not responding now. Please try again later."

    fun getNoDataError() = "Requested data is not available for now. Please try again later."

    fun getInternetError() = "Error loading data. Please check your internet connection."

    fun getGeneralError() = "Error occurred. Please try again later."
}