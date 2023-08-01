package id.belitong.bigs.compose.core.utils

import id.belitong.bigs.compose.core.data.source.local.entity.UserEntity
import id.belitong.bigs.compose.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.compose.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.compose.core.domain.model.Login
import id.belitong.bigs.compose.core.domain.model.Register
import id.belitong.bigs.compose.core.domain.model.User

object DataMapper {
    fun loginResponseToLogin(data: LoginResponse): Login = Login(
        loginResult = User(
            userId = data.loginResult.userId,
            name = data.loginResult.name,
            token = data.loginResult.token,
        ),
        message = data.message
    )

    fun registerResponseToRegister(data: RegisterResponse): Register = Register(data.message)

    fun userToUserEntity(data: User): UserEntity = UserEntity(
        userId = data.userId,
        name = data.name,
        token = data.token,
    )
}