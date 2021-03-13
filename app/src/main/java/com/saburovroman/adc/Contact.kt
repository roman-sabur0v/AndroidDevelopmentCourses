package com.saburovroman.adc

import android.graphics.Bitmap
import android.graphics.Picture
import android.media.Image

//по мере написания кода, понял, что на данном этапе этот класс особо то и не нужен,
//но пусть останется на всякий случай для дальнейшей доработки
data class Contact(val name: String,
                   val firstNumber: String,
                   val secondNumber: String? = null,
                   val firstEmail: String,
                   val secondEmail: String? = null,
                   val description: String? = null,
                   val avatar: Int = R.drawable.avatar)