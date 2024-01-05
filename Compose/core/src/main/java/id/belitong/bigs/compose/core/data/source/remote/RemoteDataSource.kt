package id.belitong.bigs.compose.core.data.source.remote

import android.util.Log
import id.belitong.bigs.compose.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.compose.core.data.source.remote.network.AuthApiService
import id.belitong.bigs.compose.core.data.source.remote.network.MainApiService
import id.belitong.bigs.compose.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.compose.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.compose.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.compose.core.data.source.remote.response.OrderItem
import id.belitong.bigs.compose.core.data.source.remote.response.PlantResponse
import id.belitong.bigs.compose.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.compose.core.data.source.remote.response.ReportItem
import id.belitong.bigs.compose.core.utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val authApiService: AuthApiService,
    private val mainApiService: MainApiService
) {
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<ApiResponse<RegisterResponse>> = flow {
        try {
            val response = authApiService.registerUser(name, email, password)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized/Sessions Expired"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        429 -> "Too Many Requests"
                        500 -> "Internal Server Error"
                        else -> {
                            e.getErrorMessage().toString()
                            Log.e(
                                "RemoteDataSource::registerUser(${e.code()})",
                                e.getErrorMessage().toString()
                            )
                        }
                    }
                    emit(ApiResponse.Error(message.toString()))
                    Log.d("Geosite", "getGeosites: $message")
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                is SocketTimeoutException -> {
                    emit(ApiResponse.Error("Request timeout"))
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
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::loginUser(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Geosite", "getGeosites: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getGeosites(): Flow<ApiResponse<List<GeositeItem>>> =
        flow {
            try {
                val response = mainApiService.getGeosites()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::getGeosites(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Geosite", "getGeosites: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getBiodiversities(): Flow<ApiResponse<List<BiodiversityItem>>> =
        flow {
            try {
                val response = mainApiService.getBiodiversities()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::getBiodiversities(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Plant", "getBiodiversities: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPlant(): Flow<ApiResponse<PlantResponse>> =
        flow {
            try {
                val response = mainApiService.getPlant()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::getPlant(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Plant", "getPlant: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getOrders(): Flow<ApiResponse<List<OrderItem>>> =
        flow {
            try {
                val response = mainApiService.getOrders()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::getOrders(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Orders", "getOrders: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getReports(): Flow<ApiResponse<List<ReportItem>>> =
        flow {
            try {
                val response = mainApiService.getReports()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val message = when (e.code()) {
                            401 -> "Unauthorized/Sessions Expired"
                            403 -> "Forbidden"
                            404 -> "Not Found"
                            429 -> "Too Many Requests"
                            500 -> "Internal Server Error"
                            else -> {
                                e.getErrorMessage().toString()
                                Log.e(
                                    "RemoteDataSource::getReports(${e.code()})",
                                    e.getErrorMessage().toString()
                                )
                            }
                        }
                        emit(ApiResponse.Error(message.toString()))
                        Log.d("Reports", "getReports: $message")
                    }

                    is UnknownHostException -> {
                        emit(ApiResponse.Error("No internet connection"))
                    }

                    is SocketTimeoutException -> {
                        emit(ApiResponse.Error("Request timeout"))
                    }

                    else -> {
                        emit(ApiResponse.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
}