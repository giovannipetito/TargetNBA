package com.petito.targetnba.di

import android.app.Application
import android.content.Context
import androidx.multidex.BuildConfig
import com.petito.targetnba.domain.repository.UsersRepository
import com.petito.targetnba.remote.UsersDataSource
import com.petito.targetnba.remote.ApiService
import com.petito.targetnba.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val cacheInterceptor = Interceptor { chain ->
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.DAYS)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Provides an instance of Context that represents the current application context.
     */
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    /**
     * Provides an instance of Cache for caching network responses. This method sets up a cache
     * directory and size for the OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(File(application.applicationContext.cacheDir, "cache"), cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun providesOkhttp(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("x-rapidapi-key", Config.API_KEY)
                    .addHeader("x-rapidapi-host", Config.BASE_URL)
                    // .header("User-Agent", Utils.getDeviceName()")
                    .addHeader("applicationId", "TARGET_NBA")
                    .addHeader("app_version", BuildConfig.VERSION_NAME)
                    .addHeader("os_version", android.os.Build.VERSION.RELEASE)
                    .build()
                chain.proceed(newRequest)
            }

        if (BuildConfig.DEBUG)
            builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides the API key used for the API requests.
     */
    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return Config.API_KEY
    }

    /**
     * Provides an instance of UsersDataSource, which is an implementation of UsersRepository
     * that retrieves data from a remote API.
     */
    @Provides
    @Singleton
    fun provideUsersDataSource(usersRepository: UsersRepository): UsersDataSource {
        return usersRepository
    }
}