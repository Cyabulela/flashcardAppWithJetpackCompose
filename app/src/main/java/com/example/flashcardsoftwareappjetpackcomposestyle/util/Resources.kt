package com.example.flashcardsoftwareappjetpackcomposestyle.util

sealed class Resources<T>(data : T? = null, message : String? = null) {
    data class Success<T>(val data: T) : Resources<T>(data)
    data class Failure<T>(val message: String) : Resources<T>(message = message)
    data class Loading<T>(val isLoading : Boolean = true) : Resources<T>()
}
