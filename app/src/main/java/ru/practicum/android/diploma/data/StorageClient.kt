package ru.practicum.android.diploma.data

interface StorageClient<T> {
    fun storeData(data: T)
    fun getData(): T?
}
