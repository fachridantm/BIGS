package id.belitong.bigs.core.data.source.remote

import android.util.Log
import id.belitong.bigs.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.core.data.source.remote.network.MainApiService
import id.belitong.bigs.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.core.utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Singleton

@Singleton
class RemoteDataSource(
    private val mainApiService: MainApiService,
) {
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
    ): Flow<ApiResponse<RegisterResponse>> = flow {
        try {
            val response =
                mainApiService.registerUser(name, email, password, phoneNumber)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = e.getErrorMessage()
                    if (message != null) {
                        emit(ApiResponse.Error(message))
                    }
                }
                else -> {
                    emit(ApiResponse.Error(e.message.toString()))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun loginUser(email: String, password: String): Flow<ApiResponse<LoginResponse>> =
        flow {
            try {
                val response = mainApiService.loginUser(email, password)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = e.getErrorMessage()
                        Log.d("Login", "loginUser: $message")
                        if (message != null) {
                            emit(ApiResponse.Error(message))
                        }
                    }
                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
}