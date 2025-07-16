package ati.su.horseraces.domain.race.model

import ati.su.horseraces.domain.common.enums.HorseEnum

data class RaceParticipant(
    val horseEnum: HorseEnum,
    val initialSpeed: Double,
    var currentSpeed: Double,
    var distanceCovered: Double = 0.0,
    var finishTimeMs: Long = 0L,
    var finalPosition: Int = 0,
    var currentProgress: Float = 0f,
    var visualXOffset: Float = 0f
)
