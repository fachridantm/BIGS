package id.belitong.bigs.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.core.data.repository.AuthRepository
import id.belitong.bigs.core.data.repository.MainRepository
import id.belitong.bigs.core.domain.repository.IAuthRepository
import id.belitong.bigs.core.domain.repository.IMainRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    abstract fun provideMainRepository(mainRepository: MainRepository): IMainRepository
}