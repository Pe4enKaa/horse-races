package ati.su.horseraces.domain.race.repository

import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.race.model.RaceResult
import ati.su.horseraces.domain.race.model.RaceResultWithParticipants
import kotlinx.coroutines.flow.Flow

interface RaceRepository {

    fun saveRaceResult(raceResult: RaceResult): Flow<DataState<Unit>>

    fun selectRaceHistory(): Flow<DataState<List<RaceResult>>>
}