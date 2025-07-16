package ati.su.horseraces.data.race_result

import ati.su.horseraces.data.common.database.HorseRacesAppDatabase
import ati.su.horseraces.data.common.module.CoroutineDispatchersModule
import ati.su.horseraces.data.common.module.DatabaseModule
import ati.su.horseraces.data.race_result.local.dao.RaceDao
import ati.su.horseraces.data.race_result.repository.RaceRepositoryImpl
import ati.su.horseraces.domain.common.utils.DispatcherIO
import ati.su.horseraces.domain.race.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, CoroutineDispatchersModule::class])
@InstallIn(SingletonComponent::class)
class RaceModule {
    @Singleton
    @Provides
    fun provideRaceDao(database: HorseRacesAppDatabase): RaceDao = database.raceDao()

    @Singleton
    @Provides
    fun provideRaceRepository(
        raceDao: RaceDao,
        @DispatcherIO ioDispatcher: CoroutineDispatcher
    ): RaceRepository = RaceRepositoryImpl(raceDao, ioDispatcher)
}