package ati.su.horseraces.presentation.ui.compose.main.history.view_model

import ati.su.horseraces.domain.common.core.ViewEvent

sealed class HistoryEvent: ViewEvent {
    data object SelectRaceResult: HistoryEvent()
}