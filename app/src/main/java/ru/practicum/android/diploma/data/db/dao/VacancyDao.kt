package ru.practicum.android.diploma.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.db.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM ${VacancyEntity.TABLE_NAME} ORDER BY insertTime DESC")
    fun getVacanciesFlow(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM ${VacancyEntity.TABLE_NAME} ORDER BY insertTime LIMIT :pageSize OFFSET :offset")
    suspend fun getVacanciesPage(offset: Int, pageSize: Int): List<VacancyEntity>

    @Query("SELECT * FROM ${VacancyEntity.TABLE_NAME} WHERE vacancyId = :id")
    suspend fun getVacancy(id: String): VacancyEntity?

    @Query("DELETE FROM ${VacancyEntity.TABLE_NAME} WHERE vacancyId = :vacancyId")
    suspend fun deleteVacancyId(vacancyId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM ${VacancyEntity.TABLE_NAME} WHERE vacancyId = :vacancyId)")
    suspend fun existsById(vacancyId: String): Boolean
}
