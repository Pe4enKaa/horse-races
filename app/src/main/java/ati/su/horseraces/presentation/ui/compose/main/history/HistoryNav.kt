package ati.su.horseraces.presentation.ui.compose.main.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryEvent
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryViewModel
import ati.su.horseraces.presentation.ui.navigation.HistoryNavigation

@Composable
fun HistoryNav() {
    val navigator = rememberNavController()
    NavHost(
        startDestination = HistoryNavigation.History,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<HistoryNavigation.History> {
            val viewModel: HistoryViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.onTriggerEvent(HistoryEvent.SelectRaceResult)
            }
            HistoryScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
            )
        }
    }
}