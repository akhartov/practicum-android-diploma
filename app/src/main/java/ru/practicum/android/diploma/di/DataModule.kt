package ru.practicum.android.diploma.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.network.VacancyApi
import ru.practicum.android.diploma.data.network.VacancyApiClient
import ru.practicum.android.diploma.data.network.VacancyApiClientImpl

val dataModule = module {

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

    factory<VacancyApiClient> { VacancyApiClientImpl(get()) }
}
