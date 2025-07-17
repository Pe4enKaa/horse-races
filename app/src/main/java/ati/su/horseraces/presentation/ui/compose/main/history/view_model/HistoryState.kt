package ati.su.horseraces.presentation.ui.compose.main.history.view_model

import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.core.ViewState
import ati.su.horseraces.domain.race_result.model.RaceResult

data class HistoryState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val raceResult: List<RaceResult> = emptyList()
): ViewState