package ru.practicum.android.diploma.presentation.model

sealed interface ToastState {
    data object NoProblem : ToastState
    data object NoInternet : ToastState
    data object ServerError : ToastState
}
