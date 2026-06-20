package edu.ucne.planetapi.di

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.planetapi.data.repository.PlanetRepositoryImpl
import edu.ucne.planetapi.domain.repository.PlanetRepository
import edu.ucne.planetapi.data.remote.DragonBallApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi =
        Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)

    @Provides
    @Singleton
    fun providePlanetRepositoryImpl(api: DragonBallApi): PlanetRepositoryImpl =
        PlanetRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository = impl
}