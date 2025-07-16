package ati.su.horseraces.domain.race.model

import androidx.room.Embedded
import androidx.room.Relation
import ati.su.horseraces.data.race_result.local.entity.RaceParticipantEntity
import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity

data class RaceResultWithParticipants(
    @Embedded val raceResult: RaceResultEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "raceId"
    )
    val participants: List<RaceParticipantEntity>
)
