package ati.su.horseraces.presentation.ui.navigation

import ati.su.horseraces.R

sealed class BottomNavigation(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
) {
    data object Race : BottomNavigation(
        route = "Race",
        title = "Скачки",
        selectedIcon = R.drawable.icon_race_selected,
        unSelectedIcon = R.drawable.icon_race_unselected,
    )

    data object History : BottomNavigation(
        route = "History",
        title = "История",
        selectedIcon = R.drawable.icon_history_selected,
        unSelectedIcon = R.drawable.icon_history_unselected,
    )
}
