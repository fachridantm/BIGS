package id.belitong.bigs.compose.core.data.repository

import id.belitong.bigs.compose.core.data.Resource.Companion.error
import id.belitong.bigs.compose.core.data.Resource.Companion.loading
import id.belitong.bigs.compose.core.data.Resource.Companion.success
import id.belitong.bigs.compose.core.data.source.local.LocalDataSource
import id.belitong.bigs.compose.core.data.source.remote.RemoteDataSource
import id.belitong.bigs.compose.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.compose.core.domain.model.User
import id.belitong.bigs.compose.core.domain.repository.IAuthRepository
import id.belitong.bigs.compose.core.utils.DataMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IAuthRepository {

    override fun getAuthToken() = localDataSource.getAuthToken()

    override fun getUserId() = localDataSource.getUserId()

    override fun getName() = localDataSource.getName()

    override fun registerUser(
        name: String,
        email: String,
        password: String,
    ) = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.registerUser(name, email, password).first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.registerResponseToRegister(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun loginUser(email: String, password: String) = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.loginUser(email, password).first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.loginResponseToLogin(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override suspend fun saveSession(token: String, user: User) {
        val userEntity = DataMapper.userToUserEntity(user)
        localDataSource.saveSession(token, userEntity)
    }

    override suspend fun deleteSession() {
        localDataSource.deleteSession()
    }
}