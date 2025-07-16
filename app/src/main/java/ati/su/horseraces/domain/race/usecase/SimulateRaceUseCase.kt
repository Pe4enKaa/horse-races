package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.domain.race.model.RaceConditions
import ati.su.horseraces.domain.race.model.RaceParticipant
import javax.inject.Inject
import kotlin.random.Random

private const val TOTAL_VIRTUAL_RACE_DISTANCE_METERS = 25.0

class SimulateRaceUseCase
@Inject constructor() {
    private val random = Random(System.currentTimeMillis())

    operator fun invoke(
        initialParticipants: List<RaceParticipant>,
        conditions: RaceConditions
    ): List<RaceParticipant> {
        val simulatedParticipants = initialParticipants.map { participant ->
            val baseSpeedFromEnum = participant.horseEnum.baseSpeed.toDouble()

            val weatherModifier = conditions.weatherCondition.speedModifier
            val trackModifier = conditions.trackCondition.speedModifier
            val jockeyBonus = (conditions.jockeySkill - 0.5) * 0.2
            val jockeySpeedModifier = 1.0 + jockeyBonus

            val effectiveSpeed = (baseSpeedFromEnum * weatherModifier * trackModifier * jockeySpeedModifier).coerceAtLeast(0.1)

            val rawTimeSeconds = TOTAL_VIRTUAL_RACE_DISTANCE_METERS / effectiveSpeed

            val randomFactor = random.nextDouble(0.9, 1.1)
            val finalTimeSeconds = rawTimeSeconds * randomFactor

            val minFinishTimeMs = 3000L

            val calculatedFinishTimeMs = (finalTimeSeconds * 1000).toLong().coerceAtLeast(minFinishTimeMs)

            participant.copy(
                finishTimeMs = calculatedFinishTimeMs,
                distanceCovered = 0.0,
                currentProgress = 0f,
                visualXOffset = 0f
            )
        }.toMutableList()

        val sortedParticipants = simulatedParticipants.sortedBy { it.finishTimeMs }
        return sortedParticipants.mapIndexed { index, participant ->
            participant.copy(finalPosition = index + 1)
        }
    }
}