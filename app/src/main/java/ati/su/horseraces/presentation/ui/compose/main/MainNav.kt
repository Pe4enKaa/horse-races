package ati.su.horseraces.presentation.ui.compose.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ati.su.horseraces.presentation.ui.compose.custom_components.BottomBarState
import ati.su.horseraces.presentation.ui.compose.custom_components.ChangeBarColors
import ati.su.horseraces.presentation.ui.compose.custom_components.rememberBottomBarState
import ati.su.horseraces.presentation.ui.compose.main.history.HistoryNav
import ati.su.horseraces.presentation.ui.compose.main.race.RaceNav
import ati.su.horseraces.presentation.ui.navigation.BottomNavigation
import ati.su.horseraces.presentation.ui.navigation.BottomNavigationUI

@Composable
fun MainNav() {
    val navBottomBarController = rememberNavController()
    val bottomBarState = rememberBottomBarState()
    ChangeBarColors(
        Color(0xFFFFFFFF),
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        MaterialTheme.colorScheme.primary
    )
    CompositionLocalProvider(
        LocalBottomBarState provides bottomBarState
    ) {
        Scaffold(bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.isVisible,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                content = {
                    BottomNavigationUI(navController = navBottomBarController)
                }
            )
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
}

val LocalBottomBarState = staticCompositionLocalOf<BottomBarState> {
    error("BottomBarState not provided")
}