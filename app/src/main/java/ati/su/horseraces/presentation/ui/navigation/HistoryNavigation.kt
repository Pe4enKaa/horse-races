package ati.su.horseraces.presentation.ui.navigation

import kotlinx.serialization.Serializable

sealed interface HistoryNavigation {
    @Serializable
    data object History : HistoryNavigation
}