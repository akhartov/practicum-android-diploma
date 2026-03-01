package ru.practicum.android.diploma.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.FavoritesChanges
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyShort

interface FavoritesInteractor {
    /**
     * Like - добавление идет в конец списка
     * */
    suspend fun like(vacancy: Vacancy)

    /**
     * Dislike - удаление из какого-то места в списке
     * */
    suspend fun dislike(vacancyId: String)

    /**
     * Запрос очередной страницы избранного
     * @param count определяется по максимальному количеству видимых элементов
     * */
    suspend fun getNewVacancies(count: Int): List<VacancyShort>

    /**
     * Когда количество элементов меняется, то нужно убрать какую-то вакансию из списка
     * избранного, или добавить при этом нужно обновить список избранного.
     * Like - добавление идет в конец списка.
     * Dislike - удаление из какого-то места в списке, чтобы понять какой,
     * нужно использовать свойство Descreased.vacancyId
     * */
    fun changesFlow(): Flow<FavoritesChanges>
}
