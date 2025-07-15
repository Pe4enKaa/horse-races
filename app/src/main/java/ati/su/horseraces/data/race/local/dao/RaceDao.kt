package ati.su.horseraces.data.race.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ati.su.horseraces.data.race.local.entity.RaceResultEntity

@Dao
interface RaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRaceResult(raceResultEntity: RaceResultEntity): Unit

    @Query("SELECT * FROM race_results ORDER BY timeCompleteRace DESC")
    suspend fun selectRaceResult(): List<RaceResultEntity>
}