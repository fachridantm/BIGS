package id.belitong.bigs.compose.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.belitong.bigs.compose.core.data.repository.AuthRepository
import id.belitong.bigs.compose.core.data.repository.GeoparkRepository
import id.belitong.bigs.compose.core.domain.repository.IAuthRepository
import id.belitong.bigs.compose.core.domain.repository.IGeoparkRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    abstract fun provideGeoparkRepository(geoparkRepository: GeoparkRepository): IGeoparkRepository
}