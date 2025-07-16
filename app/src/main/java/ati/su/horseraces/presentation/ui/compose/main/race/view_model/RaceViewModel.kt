package ati.su.horseraces.presentation.ui.compose.main.race.view_model

import ati.su.horseraces.domain.common.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject
constructor(

): BaseViewModel<RaceEvent, RaceState, Nothing>() {
    override fun setInitialState(): RaceState = RaceState()
    override fun onTriggerEvent(event: RaceEvent) {
        when(event) {

            else -> {}
        }
    }
}