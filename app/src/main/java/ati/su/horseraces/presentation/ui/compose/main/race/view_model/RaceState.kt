package ati.su.horseraces.presentation.ui.compose.main.race.view_model

import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.core.ViewState
import ati.su.horseraces.domain.common.enums.TrackCondition
import ati.su.horseraces.domain.common.enums.WeatherCondition
import ati.su.horseraces.domain.race.model.RaceParticipant
import ati.su.horseraces.domain.race.model.UiRaceResult

data class RaceState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val numberOfHorses: Int = 5,
    val selectedWeather: WeatherCondition = WeatherCondition.SUNNY,
    val selectedTrack: TrackCondition = TrackCondition.DRY,
    val jockeySkill: Float = 0.5f,
    val isRaceInProgress: Boolean = false,
    val simulatedRaceResult: UiRaceResult? = null,
    val showResultDialog: Boolean = false,
    val currentHorsesInRace: List<RaceParticipant> = emptyList()
) : ViewState {
    val canStartRace: Boolean
        get() = !isRaceInProgress && numberOfHorses > 0
}