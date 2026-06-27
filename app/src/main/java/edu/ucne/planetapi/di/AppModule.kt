package edu.ucne.planetapi.di

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.planetapi.data.remote.DragonBallApi
import edu.ucne.planetapi.data.remote.PlanetRemoteDataSource
import edu.ucne.planetapi.data.repository.PlanetRepositoryImpl
import edu.ucne.planetapi.domain.repository.PlanetRepository
import edu.ucne.planetapi.domain.repository.CharacterRepository
import edu.ucne.planetapi.data.repository.CharacterRepositoryImpl
import edu.ucne.planetapi.data.remote.CharacterRemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi {
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: PlanetRemoteDataSource): PlanetRepository {
        return PlanetRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(remoteDataSource: CharacterRemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }
}