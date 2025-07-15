package ati.su.horseraces.domain.race.usecase

import ati.su.horseraces.data.race.local.entity.RaceResultEntity
import ati.su.horseraces.domain.race.model.RaceResult
import ati.su.horseraces.domain.race.repository.RaceRepository
import javax.inject.Inject

class SaveRaceResultUseCase
@Inject constructor(private val repository: RaceRepository) {
    operator fun invoke(raceResultEntity: RaceResultEntity) = repository.saveRaceResult(raceResultEntity)
}