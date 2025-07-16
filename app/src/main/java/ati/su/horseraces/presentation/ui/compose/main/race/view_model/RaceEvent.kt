package ati.su.horseraces.presentation.ui.compose.main.race.view_model

import ati.su.horseraces.domain.common.core.ViewEvent

sealed class RaceEvent: ViewEvent {
    data object RaceAnimationFinished : RaceEvent()
    data object StartRaceClick : RaceEvent()
    data object DismissResultDialog : RaceEvent()
}