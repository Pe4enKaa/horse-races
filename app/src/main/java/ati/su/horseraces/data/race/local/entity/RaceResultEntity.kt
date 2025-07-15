package ati.su.horseraces.data.race.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "race_results")
data class RaceResultEntity(
    @PrimaryKey
    val id: String,
    val winnerHorseId: String,
    val timeCompleteRace: LocalDateTime
)
