package ru.practicum.android.diploma.data.network

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.Response
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.util.NetworkChecker
import ru.practicum.android.diploma.util.NetworkResponseStatus
import java.io.IOException

class VacancyApiClientImpl(private val api: VacancyApi, private val context: Context) : VacancyApiClient {

    override suspend fun getVacancies(options: Map<String, String>): Response {
        if (!NetworkChecker.isConnected(context)) {
            return Response().apply { resultCode = NetworkResponseStatus.NO_INTERNET }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getVacancies(options)
                response.apply { resultCode = NetworkResponseStatus.SUCCESS }
            } catch (e: IOException) {
                e.message?.let { Log.e("VacancyApiClientImpl", it) }
                Response().apply { resultCode = NetworkResponseStatus.SERVER_ERROR }
            }
        }
    }

    override suspend fun getVacancyById(id: String): Response {
        if (!NetworkChecker.isConnected(context)) {
            return Response().apply { resultCode = NetworkResponseStatus.NO_INTERNET }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getVacancyById(id)
                response.apply { resultCode = NetworkResponseStatus.SUCCESS }
            } catch (_: IOException) {
                Response().apply { resultCode = NetworkResponseStatus.SERVER_ERROR }
            }
        }
    }

    override suspend fun getFilterAreas(): List<FilterAreaDto> {
        return withContext(Dispatchers.IO) {
            api.getFilterAreas()
        }
    }

    override suspend fun getFilterIndustries(): List<FilterIndustryDto> {
        return withContext(Dispatchers.IO) {
            api.getFilterIndustries()
        }
    }
}
