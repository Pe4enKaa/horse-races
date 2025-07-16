package ati.su.horseraces.data.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ati.su.horseraces.data.race_result.local.dao.RaceDao
import ati.su.horseraces.data.race_result.local.entity.RaceParticipantEntity
import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity
import ati.su.horseraces.domain.common.utils.converts.Converts

@Database(
    entities = [RaceResultEntity::class, RaceParticipantEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(Converts::class)
abstract class HorseRacesAppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "horse_races.db"
    }

    abstract fun raceDao(): RaceDao
}
