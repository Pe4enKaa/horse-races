package ati.su.horseraces.data.race_result.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ati.su.horseraces.data.race_result.local.entity.RaceParticipantEntity
import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity
import ati.su.horseraces.domain.race_result.model.RaceResultWithParticipants
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRaceResult(raceResultEntity: RaceResultEntity): Unit

    @Query("SELECT * FROM race_results ORDER BY timeCompleteRace DESC")
    suspend fun selectAllRaceResultsWithParticipants(): List<RaceResultWithParticipants>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRaceParticipants(participants: List<RaceParticipantEntity>)

    @Transaction
    suspend fun insertFullRaceResult(raceResult: RaceResultEntity, participants: List<RaceParticipantEntity>) {
        insertRaceResult(raceResult)
        if (participants.isNotEmpty()) {
            insertRaceParticipants(participants)
        }
    }

    @Transaction
    @Query("SELECT * FROM race_results ORDER BY timeCompleteRace DESC")
    fun selectAllRaceResultsWithParticipantsFlow(): Flow<List<RaceResultWithParticipants>>
}