package id.belitong.bigs.core.utils

import id.belitong.bigs.core.data.source.local.entity.UserEntity
import id.belitong.bigs.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User

object DataMapper {
    fun loginResponseToLogin(data: LoginResponse): Login = Login(
        loginResult = User(
            data.loginResult.userId,
            data.loginResult.name,
            data.loginResult.token,
        ),
        message = data.message
    )

    fun registerResponseToRegister(data: RegisterResponse): Register = Register(data.message)

    fun userToUserEntity(data: User): UserEntity = UserEntity(
        data.userId,
        data.name,
        data.token,
    )

    fun geositeResponseToGeosite(data: GeositeItem): Geosite = Geosite(
        data.id,
        data.name,
        data.summary,
        data.type,
        data.desc,
        data.distance,
        data.img,
    )

    fun biodiversityResponseToBiodiversity(data: BiodiversityItem): Biodiversity = Biodiversity(
        data.id,
        data.name,
        data.type,
        data.img,
    )
}