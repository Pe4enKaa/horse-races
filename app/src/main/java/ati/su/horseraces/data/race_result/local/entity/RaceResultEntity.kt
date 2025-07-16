package ati.su.horseraces.data.race_result.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "race_results")
data class RaceResultEntity(
    @PrimaryKey
    val id: String,
    @Embedded
    val raceConditions: RaceConditionsEmbedded,
    val timeStartRace: LocalDateTime,
    val timeCompleteRace: LocalDateTime
)
