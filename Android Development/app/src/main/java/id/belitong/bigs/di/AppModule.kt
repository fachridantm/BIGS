package id.belitong.bigs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.core.domain.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAuthUseCase(authUseCaseImpl: AuthUseCaseImpl): AuthUseCase
}