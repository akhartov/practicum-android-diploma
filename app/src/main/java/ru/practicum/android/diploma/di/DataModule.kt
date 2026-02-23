package ru.practicum.android.diploma.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.db.dao.VacancyDao

val dataModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    single<VacancyDao> { get<AppDatabase>().vacancyDao() }

}
