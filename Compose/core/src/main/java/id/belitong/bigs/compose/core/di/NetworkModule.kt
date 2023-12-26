package id.belitong.bigs.compose.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.compose.core.BuildConfig.*
import id.belitong.bigs.compose.core.data.source.remote.network.AuthApiService
import id.belitong.bigs.compose.core.data.source.remote.network.MockApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
            )
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideAuthApiService(client: OkHttpClient): AuthApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(DICODING_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    fun provideMockApiService(client: OkHttpClient): MockApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(MOCK_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MockApiService::class.java)
    }
}