package ati.su.horseraces.domain.race_result.model

import ati.su.horseraces.domain.common.enums.HorseEnum
import ati.su.horseraces.domain.race.model.RaceConditions
import ati.su.horseraces.domain.race.model.RaceParticipant
import java.time.LocalDateTime

data class RaceResult(
    val id: String,
    val participantsResults: List<RaceParticipant>,
    val raceConditions: RaceConditions,
    val timeStartRace: LocalDateTime,
    val timeCompleteRace: LocalDateTime
) {
    val winnerHorseEnum: HorseEnum?
        get() = participantsResults.firstOrNull { it.finalPosition == 1 }?.horseEnum
}
