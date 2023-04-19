package com.marvel.common.util

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtils @Inject constructor() {

    fun formatServerDate(date: String): String {
        return try {
            val serverDateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
            val parse = serverDateFormat.parse(date)
            val clientDateFormat = SimpleDateFormat(CLIENT_DATE_FORMAT, Locale.getDefault())
            parse?.let { clientDateFormat.format(it.time) } ?: ""
        } catch (e: ParseException) {
            Timber.e(e)
            "NA"
        }
    }

    private companion object {

        const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ" // "2014-04-29T14:18:17-0400"

        const val CLIENT_DATE_FORMAT = "dd MMMM yyyy" // "08 July 2019"
    }
}