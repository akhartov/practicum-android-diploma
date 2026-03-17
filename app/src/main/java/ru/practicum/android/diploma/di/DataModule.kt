package ru.practicum.android.diploma.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.ExternalNavigatorImpl
import ru.practicum.android.diploma.data.IndustryRepositoryImpl
import ru.practicum.android.diploma.data.SearchVacanciesRepositoryImpl
import ru.practicum.android.diploma.data.StorageClient
import ru.practicum.android.diploma.data.VacancyRepositoryImpl
import ru.practicum.android.diploma.data.converters.FiltersMapper
import ru.practicum.android.diploma.data.converters.VacancyMapper
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.db.dao.VacancyDao
import ru.practicum.android.diploma.data.dto.SavedFiltersDto
import ru.practicum.android.diploma.data.impl.AreaRepositoryImpl
import ru.practicum.android.diploma.data.impl.FavoritesRepositoryImpl
import ru.practicum.android.diploma.data.impl.FilterRepositoryImpl
import ru.practicum.android.diploma.data.impl.FilterSettingsRepositoryImpl
import ru.practicum.android.diploma.data.impl.IndustrySettingsRepositoryImpl
import ru.practicum.android.diploma.data.impl.SearchParamsRepositoryImpl
import ru.practicum.android.diploma.data.network.VacancyApi
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.data.network.VacancyApiClientImpl
import ru.practicum.android.diploma.data.repository.FavoritesRepository
import ru.practicum.android.diploma.data.storage.CommonPrefsStorageClient
import ru.practicum.android.diploma.domain.api.AreaRepository
import ru.practicum.android.diploma.domain.api.ExternalNavigator
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.api.FilterSettingsRepository
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.api.IndustrySettingsRepository
import ru.practicum.android.diploma.domain.api.SearchParamsRepository
import ru.practicum.android.diploma.domain.api.SearchVacanciesRepository
import ru.practicum.android.diploma.domain.api.VacancyRepository

val dataModule = module {

    single { Gson() }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    single<VacancyRepository> {
        VacancyRepositoryImpl(get(), get())
    }

    single<VacancyDao> { get<AppDatabase>().vacancyDao() }

    single<FavoritesRepository> { FavoritesRepositoryImpl(get(), get()) }

    single<VacancyApi> {
        val headerInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
                .header("Content-Type", "application/json")
                .build()
            chain.proceed(modifiedRequest)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(VacancyApi.HOST_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VacancyApi::class.java)
    }
    single<ExternalNavigator> { ExternalNavigatorImpl(get()) }

    factory<VacancyApiClient> { VacancyApiClientImpl(get(), get()) }

    single<SearchVacanciesRepository> {
        SearchVacanciesRepositoryImpl(get(), get())
    }

    single<IndustryRepository> {
        IndustryRepositoryImpl(get(), get())
    }

    single { VacancyMapper(get()) }

    single { FiltersMapper() }

    single<AreaRepository> { AreaRepositoryImpl(get(), get()) }
    single<SharedPreferences> {
        androidContext()
            .getSharedPreferences("ANDROID_DIPLOMA_PREFERENCES", Context.MODE_PRIVATE)
    }

    single<StorageClient<SavedFiltersDto>> {
        CommonPrefsStorageClient(
            prefs = get(),
            gson = get(),
            dataKey = "SavedFiltersDto",
            type = object : TypeToken<SavedFiltersDto>() {}.type
        )
    }

    single<FilterRepository> {
        FilterRepositoryImpl(get(), get())
    }

    single<FilterSettingsRepository> { FilterSettingsRepositoryImpl(get()) }
    single<IndustrySettingsRepository> { IndustrySettingsRepositoryImpl(get()) }
    single<SearchParamsRepository> { SearchParamsRepositoryImpl(get(), get()) }
}
