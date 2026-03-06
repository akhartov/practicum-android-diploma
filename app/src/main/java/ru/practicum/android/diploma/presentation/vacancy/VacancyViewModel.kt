package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.LikeInteractor
import ru.practicum.android.diploma.domain.api.SharingInteractor
import ru.practicum.android.diploma.domain.api.VacancyInteractor
import ru.practicum.android.diploma.domain.models.MailData
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.model.LikeButtonState
import ru.practicum.android.diploma.presentation.model.VacancyDetailsState
import ru.practicum.android.diploma.util.NetworkResponseStatus

class VacancyViewModel(
    private val vacancyInteractor: VacancyInteractor,
    private val likeInteractor: LikeInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<VacancyDetailsState>(VacancyDetailsState.Loading)
    val state: StateFlow<VacancyDetailsState> = _state.asStateFlow()

    private val _stateLike = MutableStateFlow<LikeButtonState>(LikeButtonState.UnLike)
    val stateLike: StateFlow<LikeButtonState> = _stateLike.asStateFlow()

    private val currentVacancy: Vacancy?
        get() = (_state.value as? VacancyDetailsState.Content)?.details

    fun callNumber(number: String) {
        sharingInteractor.callNumber(number)
    }

    fun sendMail(data: MailData) {
        sharingInteractor.writeMail(data)
    }

    fun shareLink() {
        currentVacancy?.url?.let { url ->
            sharingInteractor.shareVacancy(url)
        }
    }

    fun getVacancy(id: String) {
        _state.value = VacancyDetailsState.Loading
        viewModelScope.launch {
            vacancyInteractor.getVacancyById(id)
                .collect { resource ->
                    processResult(id, resource.data, resource.error)
                }
            if (likeInteractor.isLiked(id)) {
                _stateLike.value = LikeButtonState.Like
                val item = likeInteractor.getLiked(id)
                if (item != null) {
                    _state.value = VacancyDetailsState.Content(item)
                }
            } else {
                _stateLike.value = LikeButtonState.UnLike
            }
        }
    }

    fun like() {
        val currentState = _state.value
        if (currentState !is VacancyDetailsState.Content) return

        viewModelScope.launch {
            val vacancy = currentState.details
            if (likeInteractor.isLiked(vacancy.id)) {
                likeInteractor.dislike(vacancy.id)
                _stateLike.value = LikeButtonState.UnLike
            } else {
                likeInteractor.like(vacancy)
                _stateLike.value = LikeButtonState.Like
            }
        }
    }

    private fun processResult(initialVacancyId: String, vacancy: Vacancy?, error: Int?) {
        if (error != null) {
            when (error) {
                NetworkResponseStatus.NO_INTERNET -> _state.value = VacancyDetailsState.NoInternet
                NetworkResponseStatus.NOT_FOUND -> {
                    _state.value = VacancyDetailsState.NotFound
                    viewModelScope.launch {
                        likeInteractor.dislike(initialVacancyId)
                    }
                }
                else -> _state.value = VacancyDetailsState.ServerError
            }
        } else if (vacancy != null) {
            _state.value = VacancyDetailsState.Content(vacancy)
        }
    }
}
