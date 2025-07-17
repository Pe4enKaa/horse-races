package ati.su.horseraces.presentation.ui.compose.main.race.view_model

import androidx.lifecycle.viewModelScope
import ati.su.horseraces.domain.common.core.BaseViewModel
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.common.core.UIComponent
import ati.su.horseraces.domain.race.model.RaceConditions
import ati.su.horseraces.domain.race_result.model.RaceResult
import ati.su.horseraces.domain.race.model.UiRaceConditions
import ati.su.horseraces.domain.race.model.UiRaceParticipant
import ati.su.horseraces.domain.race.model.UiRaceResult
import ati.su.horseraces.domain.race.usecase.GenerateRaceConditionsUseCase
import ati.su.horseraces.domain.race.usecase.GenerateRandomHorsesUseCase
import ati.su.horseraces.domain.race_result.usecase.SaveRaceResultUseCase
import ati.su.horseraces.domain.race.usecase.SimulateRaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RaceViewModel @Inject
constructor(
    private val generateRandomHorsesUseCase: GenerateRandomHorsesUseCase,
    private val generateRaceConditionsUseCase: GenerateRaceConditionsUseCase,
    private val simulateRaceUseCase: SimulateRaceUseCase,
    private val saveRaceResultUseCase: SaveRaceResultUseCase
): BaseViewModel<RaceEvent, RaceState, Nothing>() {

    private val uiDelayBeforeResultsMs = 500L

    override fun setInitialState(): RaceState = RaceState()

    init {
        generateNewRaceSet()
    }

    override fun onTriggerEvent(event: RaceEvent) {
        when (event) {
            is RaceEvent.StartRaceClick -> startRace()
            is RaceEvent.RaceAnimationFinished -> finishRace()
            is RaceEvent.DismissResultDialog -> setState { copy(showResultDialog = false) }
            else -> { }
        }
    }

    private fun generateNewRaceSet() {
        viewModelScope.launch(Dispatchers.IO) {
            val conditions = generateRaceConditionsUseCase()
            val participants = generateRandomHorsesUseCase(state.value.numberOfHorses + Random.nextInt(0, 5))
            setState {
                copy(
                    selectedWeather = conditions.weatherCondition,
                    selectedTrack = conditions.trackCondition,
                    jockeySkill = conditions.jockeySkill,
                    currentHorsesInRace = participants,
                    simulatedRaceResult = null,
                    isRaceInProgress = false,
                    showResultDialog = false
                )
            }
        }
    }

    private fun startRace() {
        if (state.value.isRaceInProgress) return

        generateNewRaceSet()

        viewModelScope.launch(Dispatchers.IO) {
            delay(200)

            val initialParticipants = state.value.currentHorsesInRace
            if (initialParticipants.isEmpty()) {
                setError { UIComponent.ToastSimple("Не удалось сгенерировать лошадей.") }
                return@launch
            }

            val raceConditions = RaceConditions(
                weatherCondition = state.value.selectedWeather,
                trackCondition = state.value.selectedTrack,
                jockeySkill = state.value.jockeySkill
            )

            val simulatedResults = simulateRaceUseCase(initialParticipants, raceConditions)

            setState {
                copy(
                    isRaceInProgress = true,
                    currentHorsesInRace = simulatedResults
                )
            }
        }
    }

    private fun finishRace() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(uiDelayBeforeResultsMs)

            val finishedParticipants = state.value.currentHorsesInRace
            val raceConditions = RaceConditions(
                weatherCondition = state.value.selectedWeather,
                trackCondition = state.value.selectedTrack,
                jockeySkill = state.value.jockeySkill
            )

            val raceResult = RaceResult(
                id = UUID.randomUUID().toString(),
                participantsResults = finishedParticipants.sortedBy { it.finalPosition },
                raceConditions = raceConditions,
                timeStartRace = LocalDateTime.now(),
                timeCompleteRace = LocalDateTime.now()
            )

            saveRaceResultUseCase(raceResult).onEach { dataState ->
                when (dataState) {
                    is DataState.Data -> {
                        setState {
                            copy(
                                isRaceInProgress = false,
                                simulatedRaceResult = mapDomainRaceResultToUi(raceResult),
                                showResultDialog = true
                            )
                        }
                    }
                    is DataState.Response -> {
                        setState { copy(isRaceInProgress = false) }
                        setError { dataState.uiComponent }
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun mapDomainRaceResultToUi(domain: RaceResult): UiRaceResult {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val durationFormatter = { ms: Long ->
            val seconds = ms / 1000.0
            String.format("%.2f s", seconds)
        }

        val winnerHorse = domain.participantsResults.firstOrNull { it.finalPosition == 1 }
        val winnerHorseEnum = winnerHorse?.horseEnum

        return UiRaceResult(
            id = domain.id.take(8),
            winnerHorseName = winnerHorseEnum?.displayName ?: "N/A",
            winnerFinishTime = winnerHorse?.let { participant -> durationFormatter(participant.finishTimeMs) } ?: "N/A",
            participantsDetails = domain.participantsResults.sortedBy { it.finalPosition }.map { participant ->
                UiRaceParticipant(
                    horseName = participant.horseEnum.displayName,
                    finishTime = durationFormatter(participant.finishTimeMs),
                    finalPosition = participant.finalPosition.toString()
                )
            },
            raceConditions = UiRaceConditions(
                weatherConditionName = domain.raceConditions.weatherCondition.displayName,
                trackConditionName = domain.raceConditions.trackCondition.displayName,
                jockeySkill = String.format("%.0f%%", domain.raceConditions.jockeySkill * 100)
            ),
            timeStartRace = domain.timeStartRace.format(timeFormatter),
            timeCompleteRace = domain.timeCompleteRace.format(timeFormatter)
        )
    }
}
