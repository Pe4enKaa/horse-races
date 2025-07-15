package ati.su.horseraces.data.race.repository

import ati.su.horseraces.data.race.local.dao.RaceDao
import ati.su.horseraces.data.race.local.entity.RaceResultEntity
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.utils.DispatcherIO
import ati.su.horseraces.domain.common.utils.handleLocalException
import ati.su.horseraces.domain.race.model.Horse
import ati.su.horseraces.domain.race.repository.RaceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RaceRepositoryImpl
@Inject
constructor(
    private val raceDao: RaceDao,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher
): RaceRepository {
    override fun saveRaceResult(raceResultEntity: RaceResultEntity): Flow<DataState<Unit>> =
        flow {
            try {
                emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))

                    val result = raceDao.insertRaceResult(raceResultEntity)
                    emit(DataState.Data(result))

            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при сохранении результата скачки"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
        }.flowOn(ioDispatcher)

    override fun getRaceHistory(): Flow<DataState<List<RaceResultEntity>>> =
        flow {
            try {
                emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))
                val result = raceDao.selectRaceResult()
                emit(DataState.Data(result))
            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при получении результата скачек"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
        }.flowOn(ioDispatcher)
}