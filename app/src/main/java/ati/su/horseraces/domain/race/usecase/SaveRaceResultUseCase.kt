package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity
import ati.su.horseraces.domain.race.model.RaceResult
import ati.su.horseraces.domain.race.repository.RaceRepository
import javax.inject.Inject

class SaveRaceResultUseCase
@Inject constructor(private val repository: RaceRepository) {
    operator fun invoke(raceResult: RaceResult) = repository.saveRaceResult(raceResult)
}