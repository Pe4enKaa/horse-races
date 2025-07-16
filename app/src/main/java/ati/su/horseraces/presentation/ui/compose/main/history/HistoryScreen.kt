package ati.su.horseraces.presentation.ui.compose.main.history

import androidx.compose.runtime.Composable
import ati.su.horseraces.domain.common.core.UIComponent
import ati.su.horseraces.presentation.ui.compose.custom_components.DefaultScreenUI
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryEvent
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryState
import kotlinx.coroutines.flow.Flow

@Composable
fun HistoryScreen(
    state: HistoryState,
    errors: Flow<UIComponent>,
    events: (HistoryEvent) -> Unit,
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        titleToolbar = "История",
    ) {

    }
}