package ati.su.horseraces.domain.race_result.usecase

import ati.su.horseraces.domain.race_result.model.RaceResult
import ati.su.horseraces.domain.race_result.repository.RaceResultRepository
import javax.inject.Inject

class SaveRaceResultUseCase
@Inject constructor(private val repository: RaceResultRepository) {
    operator fun invoke(raceResult: RaceResult) = repository.saveRaceResult(raceResult)
}