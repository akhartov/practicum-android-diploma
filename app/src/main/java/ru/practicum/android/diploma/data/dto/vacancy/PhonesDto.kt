package ru.practicum.android.diploma.data.dto.vacancy

import com.google.gson.annotations.SerializedName

data class PhonesDto(
    val comment: String? = null,
    @SerializedName("formatted")
    val phone: String? = null,
)
