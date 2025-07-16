package ati.su.horseraces.data.race_result.repository

import ati.su.horseraces.data.race_result.local.dao.RaceDao
import ati.su.horseraces.domain.common.core.DataState
import ati.su.horseraces.domain.common.core.ProgressBarState
import ati.su.horseraces.domain.common.utils.DispatcherIO
import ati.su.horseraces.domain.common.utils.handleLocalException
import ati.su.horseraces.domain.race.model.RaceResult
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
    override fun saveRaceResult(raceResult: RaceResult): Flow<DataState<Unit>> =
        flow {
            try {
                emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))

                val (raceResultEntity, participantEntities) = RaceMapper.mapToEntities(raceResult)

                raceDao.insertFullRaceResult(raceResultEntity, participantEntities)

                emit(DataState.Data(Unit))

            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при сохранении результата скачки"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
        }.flowOn(ioDispatcher)


    override fun selectRaceHistory(): Flow<DataState<List<RaceResult>>> =
        flow {
            emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))
            try {
                val list = raceDao.selectAllRaceResultsWithParticipants()
                val domainResults = list.map { RaceMapper.mapToDomain(it) }
                emit(DataState.Data(domainResults))
            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при получении результата скачек"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
        }.flowOn(ioDispatcher)
    //TODO: Реактивная реализация
    /*{
        return raceDao.getAllRaceResultsWithParticipants()
            .flowOn(ioDispatcher)
            .map { listOfPojos ->
                listOfPojos.map { RaceMapper.mapToDomain(it) }
            }
            .map { domainResults ->
                DataState.Data(domainResults)
            }
            .onStart {
                emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))
            }
            .catch { e ->
                emit(handleLocalException(e, "Ошибка при получении результата скачек"))
            }
            .onCompletion {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
    }*/
}