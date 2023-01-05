package id.belitong.bigs.core.utils

import id.belitong.bigs.core.data.source.local.entity.UserEntity
import id.belitong.bigs.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User

object DataMapper {
    fun loginResponseToLogin(data: LoginResponse): Login = Login(
        user = data.users?.get(0)?.let {
            User(
                it.id,
                it.name,
                it.email,
                it.password,
                it.phoneNumber
            )
        },
        token = data.token,
        message = data.message
    )

    fun registerResponseToRegister(data: RegisterResponse): Register = Register(data.message)

    fun userToUserEntity(data: User): UserEntity = UserEntity(
        data.id,
        data.name,
        data.email,
        data.password,
        data.phoneNumber,
    )
}