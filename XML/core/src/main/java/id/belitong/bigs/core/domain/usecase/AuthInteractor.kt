package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: IAuthRepository,
) : AuthUseCase {
    override fun getAuthToken(): Flow<String> = authRepository.getAuthToken()

    override fun getUserId(): Flow<String> = authRepository.getUserId()

    override fun getName(): Flow<String> = authRepository.getName()

    override fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<Resource<Register>> {
        return authRepository.registerUser(name, email, password)
    }

    override fun loginUser(email: String, password: String): Flow<Resource<Login>> {
        return authRepository.loginUser(email, password)
    }

    override suspend fun saveSession(token: String, user: User) {
        return authRepository.saveSession(token, user)
    }

    override suspend fun deleteSession() = authRepository.deleteSession()
}