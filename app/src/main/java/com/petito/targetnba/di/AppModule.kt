package com.petito.targetnba.di

import android.app.Application
import android.content.Context
import com.petito.targetnba.BuildConfig
import com.petito.targetnba.domain.repository.AllTeamsRepository
import com.petito.targetnba.remote.AllTeamsDataSource
import com.petito.targetnba.remote.ApiService
import com.petito.targetnba.remote.Config
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkhttp(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("x-rapidapi-key", Config.rapid_api_key)
                    .addHeader("x-rapidapi-host", "free-nba.p.rapidapi.com")
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

    private val cacheInterceptor = Interceptor { chain ->
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.DAYS)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
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

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideAllTeamsDataSource(allTeamsRepository: AllTeamsRepository): AllTeamsDataSource {
        return allTeamsRepository
    }

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(File(application.applicationContext.cacheDir, "cache"), cacheSize.toLong())
    }
}