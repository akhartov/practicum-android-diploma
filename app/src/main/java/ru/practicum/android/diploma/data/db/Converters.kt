package ru.practicum.android.diploma.data.db

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.java.KoinJavaComponent.getKoin
import kotlin.getValue

class Converters {
    private val gson: Gson by lazy { getKoin().get() }

    private val stringListDataType = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        if (value == null) {
            return null
        }

        return runCatching<List<String>> {
            gson.fromJson(value, stringListDataType)
        }.onFailure { error ->
            error.printStackTrace()
            Log.e("Converters", "Can't parse string $value")
        }.getOrNull()
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return gson.toJson(list)
    }
}
