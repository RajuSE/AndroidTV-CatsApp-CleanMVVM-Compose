package youtube.devxraju.catsforever.di

import youtube.devxraju.catsforever.data.repository.CatsRepositoryImpl
import youtube.devxraju.catsforever.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatsRepository(catsRepositoryImpl: CatsRepositoryImpl): CatsRepository

}