package com.nazmul.hiltmvvmapp.common

sealed class Resource<out R>{
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()

}
