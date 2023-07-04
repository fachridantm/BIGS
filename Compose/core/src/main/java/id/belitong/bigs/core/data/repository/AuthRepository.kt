package id.belitong.bigs.core.data.repository

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.data.source.local.LocalDataSource
import id.belitong.bigs.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.domain.repository.IAuthRepository
import id.belitong.bigs.core.data.source.remote.RemoteDataSource
import id.belitong.bigs.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IAuthRepository {

    override fun getAuthToken(): Flow<String> = localDataSource.getAuthToken()

    override fun getUserId(): Flow<String> = localDataSource.getUserId()

    override fun getName(): Flow<String> = localDataSource.getName()

    override fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<Resource<Register>> = flow {
        emit(Resource.Loading)
        when (val apiResponse =
            remoteDataSource.registerUser(name, email, password).first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.registerResponseToRegister(apiResponse.data)
                emit(Resource.Success(data))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun loginUser(email: String, password: String): Flow<Resource<Login>> = flow {
        emit(Resource.Loading)
        when (val apiResponse = remoteDataSource.loginUser(email, password).first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.loginResponseToLogin(apiResponse.data)
                emit(Resource.Success(data))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
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