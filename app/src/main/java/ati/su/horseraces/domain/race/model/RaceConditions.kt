package ati.su.horseraces.domain.race.model

import ati.su.horseraces.domain.common.enums.TrackCondition
import ati.su.horseraces.domain.common.enums.WeatherCondition

data class RaceConditions(
    val weatherCondition: WeatherCondition,
    val trackCondition: TrackCondition,
    val jockeySkill: Float
)
