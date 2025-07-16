package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.domain.common.enums.HorseEnum
import ati.su.horseraces.domain.race.model.RaceParticipant
import javax.inject.Inject
import kotlin.random.Random

class GenerateRandomHorsesUseCase @Inject constructor() {
    private val random = Random(System.currentTimeMillis())

    operator fun invoke(count: Int): List<RaceParticipant> {
        if (count <= 0) return emptyList()

        val allHorsesEnums = HorseEnum.entries.toMutableList()
        val selectedHorseEnums = mutableListOf<HorseEnum>()

        while (selectedHorseEnums.size < count && allHorsesEnums.isNotEmpty()) {
            val randomIndex = random.nextInt(allHorsesEnums.size)
            val horseEnum = allHorsesEnums.removeAt(randomIndex)
            selectedHorseEnums.add(horseEnum)
        }

        val participants = selectedHorseEnums.map { horseEnum ->
            RaceParticipant(
                horseEnum = horseEnum,
                initialSpeed = horseEnum.baseSpeed.toDouble(),
                currentSpeed = horseEnum.baseSpeed.toDouble(),
                distanceCovered = 0.0,
                finishTimeMs = 0L,
                finalPosition = 0,
                currentProgress = 0f
            )
        }
        return participants
    }
}