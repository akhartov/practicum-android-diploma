package ru.practicum.android.diploma.data.dto.vacancy

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.domain.models.Phones

data class PhonesDto(
    val comment: String? = null,
    @SerializedName("formatted")
    val phone: String? = null,
)

fun PhonesDto.toPhone(): Phones {
    return Phones(
        comment = this.comment,
        phone = this.phone
    )
}
