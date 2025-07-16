package ati.su.horseraces.domain.race.model

data class UiRaceResult(
    val id: String,
    val winnerHorseName: String,
    val winnerFinishTime: String,
    val participantsDetails: List<UiRaceParticipant>,
    val raceConditions: UiRaceConditions,
    val timeStartRace: String,
    val timeCompleteRace: String
)
