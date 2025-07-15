package ati.su.horseraces.domain.race.model

import java.time.LocalDateTime

data class RaceResult(
    val id: String,
    val winnerHorse: Horse,
    val participants: List<Horse>,
    val timestamp: LocalDateTime
)
