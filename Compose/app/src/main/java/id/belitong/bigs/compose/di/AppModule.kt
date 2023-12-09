package id.belitong.bigs.compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.compose.core.domain.usecase.AuthInteractor
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import id.belitong.bigs.compose.core.domain.usecase.GeoparkInteractor
import id.belitong.bigs.compose.core.domain.usecase.GeoparkUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    @Singleton
    abstract fun provideGeoparkUseCase(geoparkInteractor: GeoparkInteractor): GeoparkUseCase
}