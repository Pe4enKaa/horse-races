package ati.su.horseraces.presentation.ui.compose.main.history.view_model

import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.core.ViewState

data class HistoryState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
): ViewState