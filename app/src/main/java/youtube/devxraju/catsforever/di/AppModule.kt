package youtube.devxraju.catsforever.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import youtube.devxraju.catsforever.data.local.CatsDao
import youtube.devxraju.catsforever.data.local.CatsDatabase
import youtube.devxraju.catsforever.data.remote.CatsApi
import youtube.devxraju.catsforever.util.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiInstance(): CatsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): CatsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = CatsDatabase::class.java,
            name = "cats_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        catsDatabase: CatsDatabase
    ): CatsDao = catsDatabase.catsDao

}