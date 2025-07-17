package ati.su.horseraces.domain.race_result.repository

import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.race_result.model.RaceResult
import kotlinx.coroutines.flow.Flow

interface RaceResultRepository {

    fun saveRaceResult(raceResult: RaceResult): Flow<DataState<Unit>>

    fun selectRaceResult(): Flow<DataState<List<RaceResult>>>
}