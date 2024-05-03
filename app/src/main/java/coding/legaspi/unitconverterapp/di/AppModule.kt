package coding.legaspi.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import coding.legaspi.unitconverterapp.data.database.ConvertedDatabase
import coding.legaspi.unitconverterapp.data.repository.ConverterRepository
import coding.legaspi.unitconverterapp.data.repositoryImpl.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Karlen Legaspi
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(application : Application) : ConvertedDatabase{
        return Room.databaseBuilder(
            application,
            ConvertedDatabase::class.java,
            "converter_data_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConverterRepository(converterDatabase: ConvertedDatabase) : ConverterRepository{
        return ConverterRepositoryImpl(converterDatabase.converterDAO)
    }
}