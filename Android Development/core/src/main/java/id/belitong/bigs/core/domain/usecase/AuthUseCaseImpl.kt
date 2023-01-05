package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: IAuthRepository,
) : AuthUseCase {
    override fun getToken(): Flow<String> = authRepository.getToken()

    override fun getId(): Flow<Int> = authRepository.getId()

    override fun getName(): Flow<String> = authRepository.getName()

    override fun getEmail(): Flow<String> = authRepository.getEmail()

    override fun getPhoneNumber(): Flow<String> = authRepository.getPhoneNumber()

    override fun registerUser(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
    ): Flow<Resource<Register>> {
        return authRepository.registerUser(name, email, password, phoneNumber)
    }

    override fun loginUser(email: String, password: String): Flow<Resource<Login>> {
        return authRepository.loginUser(email, password)
    }

    override suspend fun saveSession(token: String, user: User) {
        return authRepository.saveSession(token, user)
    }

    override suspend fun deleteSession() = authRepository.deleteSession()
}