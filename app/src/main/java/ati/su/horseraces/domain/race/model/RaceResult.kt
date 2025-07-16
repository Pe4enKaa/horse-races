package ati.su.horseraces.domain.race.model

import ati.su.horseraces.domain.common.enums.HorseEnum
import java.time.LocalDateTime

data class RaceResult(
    val id: String,
    val participantsResults: List<RaceParticipant>,
    val raceConditions: RaceConditions,
    val timeStartRace: LocalDateTime,
    val timeCompleteRace: LocalDateTime
) {
    val winnerHorseEnum: HorseEnum?
        get() = participantsResults.firstOrNull { it.finalPosition == 1 }?.horse
}
