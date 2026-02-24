package ru.practicum.android.diploma.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
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

    @Query("SELECT * FROM ${VacancyEntity.TABLE_NAME} WHERE vacancyId = :id")
    suspend fun getVacancy(id: String): VacancyEntity?

    @Delete
    suspend fun deleteVacancy(vacancy: VacancyEntity)
}
