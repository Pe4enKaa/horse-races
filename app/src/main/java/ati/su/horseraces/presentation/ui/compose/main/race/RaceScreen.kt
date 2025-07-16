package ati.su.horseraces.presentation.ui.compose.main.race

import androidx.compose.runtime.Composable
import ati.su.horseraces.domain.common.core.UIComponent
import ati.su.horseraces.presentation.ui.compose.custom_components.DefaultScreenUI
import ati.su.horseraces.presentation.ui.compose.main.race.view_model.RaceEvent
import ati.su.horseraces.presentation.ui.compose.main.race.view_model.RaceState
import kotlinx.coroutines.flow.Flow

@Composable
fun RaceScreen(
    state: RaceState,
    errors: Flow<UIComponent>,
    events: (RaceEvent) -> Unit,
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        titleToolbar = "Скачки",
    ) {

    }
}