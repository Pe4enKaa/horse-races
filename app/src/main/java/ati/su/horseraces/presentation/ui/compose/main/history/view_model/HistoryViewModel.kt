package ati.su.horseraces.presentation.ui.compose.main.history.view_model

import androidx.lifecycle.viewModelScope
import ati.su.horseraces.domain.common.core.BaseViewModel
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.race_result.usecase.SelectRaceResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
@Inject
constructor(
    private val selectRaceResultUseCase: SelectRaceResultUseCase,
) : BaseViewModel<HistoryEvent, HistoryState, Nothing>() {
    override fun setInitialState(): HistoryState = HistoryState()

    override fun onTriggerEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.SelectRaceResult -> {
                selectRaceResult()
            }
        }
    }

    private fun selectRaceResult() {
        selectRaceResultUseCase
            .invoke()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Data -> {
                        dataState.data?.let {
                            setState { copy(raceResult = it) }
                        }
                    }
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }
}