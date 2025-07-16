package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.domain.race.repository.RaceRepository
import javax.inject.Inject

class GetRaceHistoryUseCase
@Inject
constructor(private val raceRepository: RaceRepository) {
    operator fun invoke() = raceRepository.selectRaceHistory()
}