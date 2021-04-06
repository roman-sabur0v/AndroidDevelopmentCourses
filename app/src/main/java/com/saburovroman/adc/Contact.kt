package com.saburovroman.adc

import androidx.annotation.DrawableRes

data class Contact(
    val fullName: String,
    val firstNum: String,
    val secondNum: String,
    val firstEmail: String,
    val secondEmail: String,
    val description: String,
    @DrawableRes val avatar: Int
)