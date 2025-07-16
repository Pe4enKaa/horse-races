package ati.su.horseraces.presentation.ui.navigation

import kotlinx.serialization.Serializable

sealed interface RaceNavigation {
    @Serializable
    data object Race : RaceNavigation
}