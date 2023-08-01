package id.belitong.bigs.compose.core.domain.repository

import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.domain.model.Login
import id.belitong.bigs.compose.core.domain.model.Register
import id.belitong.bigs.compose.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun getAuthToken(): Flow<String>

    fun getUserId(): Flow<String>

    fun getName(): Flow<String>

    fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<Resource<Register>>

    fun loginUser(email: String, password: String): Flow<Resource<Login>>

    suspend fun saveSession(token: String, user: User)

    suspend fun deleteSession()
}