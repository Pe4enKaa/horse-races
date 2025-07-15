package ati.su.horseraces.data.common.module

import android.app.Application
import androidx.room.Room
import ati.su.horseraces.data.common.database.HorseRacesAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): HorseRacesAppDatabase {
        return Room.databaseBuilder(
            application,
            HorseRacesAppDatabase::class.java,
            HorseRacesAppDatabase.DB_NAME
        ).fallbackToDestructiveMigration(false)
            .build()
    }
}