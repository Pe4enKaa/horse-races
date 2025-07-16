package ati.su.horseraces.data.race_result.local.entity

data class RaceConditionsEmbedded(
    val weatherCondition: Long,
    val trackCondition: Long,
    val jockeySkill: Float
)
