package ru.practicum.android.diploma.data.network

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.data.Response
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.util.NetworkChecker
import ru.practicum.android.diploma.util.NetworkResponseStatus

class VacancyApiClientImpl(private val api: VacancyApi, private val context: Context) : VacancyApiClient {
    override suspend fun getVacancies(options: Map<String, String>): Response {
        return withContext(Dispatchers.IO) {
            withNetworkErrorHandling {
                api.getVacancies(options)
            }
        }
    }

    override suspend fun getVacancyById(id: String): Response {
        return withContext(Dispatchers.IO) {
            withNetworkErrorHandling {
                api.getVacancyById(id)
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

    private suspend fun withNetworkErrorHandling(
        block: suspend () -> Response
    ): Response {
        return runCatching {
            if (!NetworkChecker.isConnected(context)) {
                return Response().apply { resultCode = NetworkResponseStatus.NO_INTERNET }
            }

            block().apply { resultCode = NetworkResponseStatus.SUCCESS }
        }.getOrElse { exception ->
            throwableToResponse(exception)
        }
    }

    private fun throwableToResponse(throwable: Throwable): Response {
        throwable.message?.let { Log.e("VacancyApiClientImpl", it) }

        return when (throwable) {
            is HttpException -> {
                Response().apply {
                    resultCode = when (throwable.code()) {
                        HTTP_NOT_FOUND -> NetworkResponseStatus.NOT_FOUND
                        else -> NetworkResponseStatus.SERVER_ERROR
                    }
                }
            }

            else -> {
                Response().apply { resultCode = NetworkResponseStatus.SERVER_ERROR }
            }
        }
    }

    companion object {
        const val HTTP_NOT_FOUND = 404
    }
}
