package ati.su.horseraces.domain.race.repository

import ati.su.horseraces.data.race.local.entity.RaceResultEntity
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.race.model.Horse
import ati.su.horseraces.domain.race.model.RaceResult
import kotlinx.coroutines.flow.Flow

interface RaceRepository {

    fun saveRaceResult(raceResultEntity: RaceResultEntity): Flow<DataState<Unit>>

    fun getRaceHistory(): Flow<DataState<List<RaceResultEntity>>>
}