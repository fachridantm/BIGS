package id.belitong.bigs.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
            )
//            .addInterceptor { chain ->
//                chain.request().newBuilder()
//                    .addHeader("Authorization","Bearer ${BuildConfig.ROKET_API_KEY}")
//                    .build()
//                    .let(chain::proceed)
//            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

//    @Provides
//    fun provideMainApiService(client: OkHttpClient): MainApiService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.MAIN_BASE_URL) // TODO: BuildConfig.MAIN_BASE_URL -> Add MAIN_BASE_URL to local.properties
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(MainApiService::class.java)
//    }
}