package com.ramanbyte.pibm_in.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.ramanbyte.pibm_in.data.local.NavigationDao
import com.ramanbyte.pibm_in.data.local.PibmDatabase
import com.ramanbyte.pibm_in.data.remote.PibmApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
    
    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }
    
    @Provides
    @Singleton
    fun providePibmDatabase(@ApplicationContext context: Context): PibmDatabase {
        return Room.databaseBuilder(
            context,
            PibmDatabase::class.java,
            "pibm_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideNavigationDao(database: PibmDatabase): NavigationDao {
        return database.navigationDao()
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    @Provides
    @Singleton
    fun providePibmApi(okHttpClient: OkHttpClient): PibmApi {
        return Retrofit.Builder()
            .baseUrl(PibmApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PibmApi::class.java)
    }
}
