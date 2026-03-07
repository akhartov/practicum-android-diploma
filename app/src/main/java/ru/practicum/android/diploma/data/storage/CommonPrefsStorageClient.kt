package ru.practicum.android.diploma.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import ru.practicum.android.diploma.data.StorageClient
import java.lang.reflect.Type

class CommonPrefsStorageClient<T>(
    private val prefs: SharedPreferences,
    private val gson: Gson,
    private val dataKey: String,
    private val type: Type
) : StorageClient<T> {

    override fun storeData(data: T) {
        prefs.edit {
            putString(
                dataKey,
                gson.toJson(data, type)
            )
        }
    }

    override fun getData(): T? {
        val dataJson = prefs.getString(dataKey, null) ?: return null
        return gson.fromJson(dataJson, type)
    }
}
