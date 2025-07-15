package ati.su.horseraces.domain.common.core

sealed class ProgressBarState {
    data object ButtonLoading : ProgressBarState()

    data object ScreenLoading : ProgressBarState()

    data object Idle : ProgressBarState()
}
