package org.wahid.instabugweatherapp.utils

interface Callback<T> {
    fun onSuccess(result: T)
    fun onError(error: Throwable)
}