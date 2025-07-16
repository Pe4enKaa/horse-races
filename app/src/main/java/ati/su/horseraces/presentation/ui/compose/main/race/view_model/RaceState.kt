package ati.su.horseraces.presentation.ui.compose.main.race.view_model

import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.core.ViewState

data class RaceState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
): ViewState