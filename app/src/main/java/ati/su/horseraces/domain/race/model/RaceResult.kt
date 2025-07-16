package ati.su.horseraces.domain.race.model

import ati.su.horseraces.domain.common.enums.HorseEnum
import java.time.LocalDateTime

data class RaceResult(
    val id: String,
    val winnerHorse: HorseEnum,
    val participants: List<RaceParticipant>,
    val raceConditions: RaceConditions,
    val timestamp: LocalDateTime
)
