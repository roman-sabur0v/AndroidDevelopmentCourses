package com.saburovroman.adc

interface ContactListReceivable<T> {
    fun getList(list: List<T>)
}