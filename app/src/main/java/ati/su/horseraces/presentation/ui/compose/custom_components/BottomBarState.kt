package ati.su.horseraces.presentation.ui.compose.custom_components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class BottomBarState(initialVisibility: Boolean) {
    var isVisible by mutableStateOf(initialVisibility)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}

@Composable
fun rememberBottomBarState(initialVisibility: Boolean = true) =
    remember { BottomBarState(initialVisibility) }