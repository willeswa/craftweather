package com.wanjala.weathercraft.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wanjala.weathercraft.BuildConfig
import com.wanjala.weathercraft.data.repositories.MainRepositoryImpl
import com.wanjala.weathercraft.data.sources.local.db.AppDatabase
import com.wanjala.weathercraft.data.sources.local.db.WeatherDao
import com.wanjala.weathercraft.data.sources.local.session.SessionManagerImpl
import com.wanjala.weathercraft.data.sources.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weathercraft_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManagerImpl {
        return SessionManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("appid", BuildConfig.WEATHER_API_KEY) // Use BuildConfig.WEATHER_API_KEY
                    .build()
                val request = original.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/") // Weather API base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        sessionManager: SessionManagerImpl,
        weatherApiService: WeatherApiService,
        weatherDao: WeatherDao
    ): MainRepositoryImpl {
        return MainRepositoryImpl(sessionManager, weatherApiService, weatherDao)
    }
}
