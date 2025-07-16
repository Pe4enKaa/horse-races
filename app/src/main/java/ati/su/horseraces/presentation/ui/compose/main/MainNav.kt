package ati.su.horseraces.presentation.ui.compose.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ati.su.horseraces.presentation.ui.compose.custom_components.ChangeBarColors
import ati.su.horseraces.presentation.ui.compose.main.history.HistoryNav
import ati.su.horseraces.presentation.ui.compose.main.race.RaceNav
import ati.su.horseraces.presentation.ui.navigation.BottomNavigation
import ati.su.horseraces.presentation.ui.navigation.BottomNavigationUI

@Composable
fun MainNav() {
    val navBottomBarController = rememberNavController()
    ChangeBarColors(
        Color(0xFFFFFFFF),
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        MaterialTheme.colorScheme.primary
    )
    Scaffold(bottomBar = {
        BottomNavigationUI(navController = navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = BottomNavigation.Race.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize(),
            ) {
                composable(route = BottomNavigation.Race.route) {
                    RaceNav()
                }
                composable(route = BottomNavigation.History.route) {
                    HistoryNav()
                }
            }
        }
    }
}