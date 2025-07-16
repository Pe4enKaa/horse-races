package ati.su.horseraces.presentation.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ati.su.horseraces.presentation.ui.compose.main.MainNav
import ati.su.horseraces.presentation.ui.navigation.AppNavigation
import ati.su.horseraces.presentation.ui.theme.HorseRacesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorseRacesTheme {
                val navigator = rememberNavController()
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navigator,
                        startDestination = AppNavigation.Main,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        composable<AppNavigation.Splash> {
                            //TODO: для сплэш экрана
                        }
                        composable<AppNavigation.Main> {
                            MainNav()
                        }
                    }
                }
            }
        }
    }
}