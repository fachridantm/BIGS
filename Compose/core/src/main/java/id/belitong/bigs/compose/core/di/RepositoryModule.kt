package id.belitong.bigs.compose.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.compose.core.data.repository.AuthRepository
import id.belitong.bigs.compose.core.domain.repository.IAuthRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository
}