package id.belitong.bigs.core.utils

import id.belitong.bigs.core.data.source.local.entity.UserEntity
import id.belitong.bigs.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.core.data.source.remote.response.OrderItem
import id.belitong.bigs.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.model.Register
import id.belitong.bigs.core.domain.model.User

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
        userId =  data.userId,
        name = data.name,
        token = data.token,
    )

    fun geositeResponseToGeosite(data: GeositeItem): Geosite = Geosite(
        id = data.id,
        name = data.name,
        summary = data.summary,
        type = data.type,
        desc = data.desc,
        plant = data.plant ?: "-",
        animal = data.animal ?: "-",
        distance = data.distance,
        location = data.location,
        hours = data.hours,
        img = data.img,
    )

    fun biodiversityResponseToBiodiversity(data: BiodiversityItem): Biodiversity = Biodiversity(
        id = data.id,
        name = data.name,
        type = data.type,
        location = data.location ?: "-",
        img = data.img,
    )

    fun orderResponseToOrder(data: OrderItem): Order = Order(
        id = data.id,
        geositeName = data.geositeName,
        geositeImage = data.geositeImage,
        tourGuideName = data.tourGuideName,
        tourGuidePhone = data.tourGuidePhone,
        bookingDate = data.bookingDate,
        tourDate = data.tourDate,
        programName = data.programName,
        status = data.status,
    )
}