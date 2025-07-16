package ati.su.horseraces.data.race_result.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "race_participants",
    foreignKeys = [
        ForeignKey(
            entity = RaceResultEntity::class,
            parentColumns = ["id"],
            childColumns = ["raceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["raceId"])]
)
data class RaceParticipantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val raceId: String,
    val horseEnumId: Long,
    val finishTimeMs: Long,
    val finalPosition: Int
)
