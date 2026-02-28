package ru.practicum.android.diploma.util

sealed class Resource<T>(val data: T? = null, val error: Int? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: Int) : Resource<T>(data = null, error = error)
}
