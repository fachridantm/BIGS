package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: IAuthRepository,
) : AuthUseCase {
    override fun getAuthToken() = authRepository.getAuthToken()
    override fun getUserId() = authRepository.getUserId()
    override fun getName() = authRepository.getName()
    override fun registerUser(
        name: String,
        email: String,
        password: String,
    ) = authRepository.registerUser(name, email, password)

    override fun loginUser(
        email: String,
        password: String
    ) = authRepository.loginUser(email, password)

    override suspend fun saveSession(
        token: String,
        user: User
    ) = authRepository.saveSession(token, user)

    override suspend fun deleteSession() = authRepository.deleteSession()
}