package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.Response

data class AreaResponse(
    val areas: List<FilterAreaDto>
) : Response()
