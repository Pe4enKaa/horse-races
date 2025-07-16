package ati.su.horseraces.presentation.ui.compose.main.history.view_model

import ati.su.horseraces.domain.common.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
@Inject
constructor(

): BaseViewModel<HistoryEvent, HistoryState, Nothing>(){
    override fun setInitialState(): HistoryState = HistoryState()

    override fun onTriggerEvent(event: HistoryEvent) {
        when(event) {

            else -> {}
        }
    }
}