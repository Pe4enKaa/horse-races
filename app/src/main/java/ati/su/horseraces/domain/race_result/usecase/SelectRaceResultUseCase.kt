package ati.su.horseraces.domain.race_result.usecase

import ati.su.horseraces.domain.race_result.repository.RaceResultRepository
import javax.inject.Inject

class SelectRaceResultUseCase
@Inject
constructor(private val raceResultRepository: RaceResultRepository) {
    operator fun invoke() = raceResultRepository.selectRaceResult()
}