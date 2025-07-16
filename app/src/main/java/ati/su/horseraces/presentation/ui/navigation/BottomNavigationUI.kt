package ati.su.horseraces.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ati.su.horseraces.presentation.ui.theme.DefaultCardColorsTheme
import ati.su.horseraces.presentation.ui.theme.DefaultNavigationBarItemTheme

@Composable
fun BottomNavigationUI(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavigation.Race,
        BottomNavigation.History,
    )
    Card(
        colors = DefaultCardColorsTheme(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp,
        ) {
            items.forEach { destination ->
                NavigationBarItem(
                    label = { Text(text = destination.title) },
                    colors = DefaultNavigationBarItemTheme(),
                    selected = destination.route == currentRoute,
                    icon = {
                        Icon(
                            painterResource(
                                if (destination.route == currentRoute) destination.selectedIcon else destination.unSelectedIcon
                            ),
                            destination.title,
                        )
                    },
                    onClick = {
                        if (currentRoute != destination.route) {
                            navController.navigate(destination.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}