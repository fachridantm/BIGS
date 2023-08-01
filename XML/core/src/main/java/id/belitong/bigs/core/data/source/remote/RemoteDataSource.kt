package id.belitong.bigs.core.data.source.remote

import android.util.Log
import id.belitong.bigs.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.core.data.source.remote.network.AuthApiService
import id.belitong.bigs.core.data.source.remote.network.MainApiService
import id.belitong.bigs.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.core.data.source.remote.response.OrderItem
import id.belitong.bigs.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.core.utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val mainApiService: MainApiService,
    private val authApiService: AuthApiService
) {
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<ApiResponse<RegisterResponse>> = flow {
        try {
            val response =
                authApiService.registerUser(name, email, password)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = e.getErrorMessage()
                    if (message != null) {
                        emit(ApiResponse.Error(message))
                    }
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
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
                val response = authApiService.loginUser(email, password)
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

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)


    suspend fun getAllGeosites(): Flow<ApiResponse<List<GeositeItem>>> = flow {
        try {
            val response = mainApiService.getAllGeosites()
            val geosites = response.items
            if (geosites != null) {
                emit(ApiResponse.Success(geosites))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllBiodiversity(): Flow<ApiResponse<List<BiodiversityItem>>> = flow {
        try {
            val response = mainApiService.getAllBiodiversity()
            val biodiversity = response.items
            if (biodiversity != null) {
                emit(ApiResponse.Success(biodiversity))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllOrder(): Flow<ApiResponse<List<OrderItem>>> = flow {
        try {
            val response = mainApiService.getAllOrder()
            val order = response.items
            if (order != null) {
                emit(ApiResponse.Success(order))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
}