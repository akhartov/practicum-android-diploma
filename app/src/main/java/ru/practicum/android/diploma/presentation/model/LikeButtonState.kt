package ru.practicum.android.diploma.presentation.model

sealed interface LikeButtonState {
    object Like : LikeButtonState
    object UnLike : LikeButtonState
}
