package com.marvel.home.domain.model

data class HomeErrorData(
    val message: String,
    val isMainError: Boolean // error for e.g. no data got loaded
)