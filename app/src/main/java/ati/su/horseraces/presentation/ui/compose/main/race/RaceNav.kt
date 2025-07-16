package ati.su.horseraces.presentation.ui.compose.main.race

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ati.su.horseraces.presentation.ui.compose.main.race.view_model.RaceViewModel
import ati.su.horseraces.presentation.ui.navigation.RaceNavigation

@Composable
fun RaceNav() {
    val navigator = rememberNavController()
    NavHost(
        startDestination = RaceNavigation.Race,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<RaceNavigation.Race> {
            val viewModel: RaceViewModel = hiltViewModel()
            RaceScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
            )
        }
    }
}
