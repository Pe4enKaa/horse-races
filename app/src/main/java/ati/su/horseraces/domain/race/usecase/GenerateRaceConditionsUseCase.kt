package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.domain.common.enums.TrackCondition
import ati.su.horseraces.domain.common.enums.WeatherCondition
import ati.su.horseraces.domain.race.model.RaceConditions
import javax.inject.Inject
import kotlin.random.Random

class GenerateRaceConditionsUseCase
@Inject constructor() {
    private val random = Random(System.currentTimeMillis())

    operator fun invoke(): RaceConditions {
        val randomWeather = WeatherCondition.entries.random(random)
        val randomTrack = TrackCondition.entries.random(random)
        val randomJockeySkill = random.nextFloat()

        return RaceConditions(
            weatherCondition = randomWeather,
            trackCondition = randomTrack,
            jockeySkill = randomJockeySkill
        )
    }
}