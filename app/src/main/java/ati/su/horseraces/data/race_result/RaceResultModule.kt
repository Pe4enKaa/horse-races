package ati.su.horseraces.data.race_result

import ati.su.horseraces.data.common.database.HorseRacesAppDatabase
import ati.su.horseraces.data.common.module.CoroutineDispatchersModule
import ati.su.horseraces.data.common.module.DatabaseModule
import ati.su.horseraces.data.race_result.local.dao.RaceResultDao
import ati.su.horseraces.data.race_result.repository.RaceResultResultRepositoryImpl
import ati.su.horseraces.domain.common.utils.DispatcherIO
import ati.su.horseraces.domain.race_result.repository.RaceResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, CoroutineDispatchersModule::class])
@InstallIn(SingletonComponent::class)
class RaceResultModule {
    @Singleton
    @Provides
    fun provideRaceDao(database: HorseRacesAppDatabase): RaceResultDao = database.raceDao()

    @Singleton
    @Provides
    fun provideRaceRepository(
        raceResultDao: RaceResultDao,
        @DispatcherIO ioDispatcher: CoroutineDispatcher
    ): RaceResultRepository = RaceResultResultRepositoryImpl(raceResultDao, ioDispatcher)
}