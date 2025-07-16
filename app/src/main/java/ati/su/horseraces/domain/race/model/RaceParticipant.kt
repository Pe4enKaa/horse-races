package ati.su.horseraces.domain.race.model

import ati.su.horseraces.domain.common.enums.HorseEnum

data class RaceParticipant(
    val horse: HorseEnum,
    val finishTimeMs: Long,
    val finalPosition: Int
)
