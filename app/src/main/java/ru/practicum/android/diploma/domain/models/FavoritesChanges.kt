package ru.practicum.android.diploma.domain.models

sealed interface FavoritesChanges {
    data object Empty : FavoritesChanges
    data class Increased(val vacancyId: String) : FavoritesChanges
    data class Decreased(val vacancyId: String) : FavoritesChanges
}
