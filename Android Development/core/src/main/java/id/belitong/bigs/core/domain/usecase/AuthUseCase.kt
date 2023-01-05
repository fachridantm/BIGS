package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun getToken(): Flow<String>

    fun getId(): Flow<Int>

    fun getName(): Flow<String>

    fun getEmail(): Flow<String>

    fun getPhoneNumber(): Flow<String>

    fun registerUser(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
    ): Flow<Resource<Register>>

    fun loginUser(email: String, password: String): Flow<Resource<Login>>

    suspend fun saveSession(token: String, user: User)

    suspend fun deleteSession()
}