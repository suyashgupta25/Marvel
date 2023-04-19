package com.marvel.home.ui.model

internal data class SnackBarStatus(
    val message: String,
    val shouldShow: Boolean
) {

    companion object {

        val EMPTY = SnackBarStatus(
            message = "",
            shouldShow = false
        )
    }
}