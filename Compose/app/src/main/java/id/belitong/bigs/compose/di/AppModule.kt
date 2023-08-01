package id.belitong.bigs.compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.compose.core.domain.usecase.AuthInteractor
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase
}