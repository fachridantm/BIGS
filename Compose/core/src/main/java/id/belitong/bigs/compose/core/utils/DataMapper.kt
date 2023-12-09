package id.belitong.bigs.compose.core.utils

import id.belitong.bigs.compose.core.data.source.local.entity.UserEntity
import id.belitong.bigs.compose.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.compose.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.compose.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.compose.core.data.source.remote.response.OrderItem
import id.belitong.bigs.compose.core.data.source.remote.response.PlantItem
import id.belitong.bigs.compose.core.data.source.remote.response.RegisterResponse
import id.belitong.bigs.compose.core.data.source.remote.response.ReportItem
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.domain.model.Login
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.domain.model.Plant
import id.belitong.bigs.compose.core.domain.model.Register
import id.belitong.bigs.compose.core.domain.model.Report
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
        photoUrl = data.photoUrl,
    )

    fun geositeItemToGeosite(data: List<GeositeItem>): List<Geosite> = data.map {
        Geosite(
            id = it.id,
            name = it.name,
            summary = it.summary,
            type = it.type,
            desc = it.desc,
            plant = it.plant,
            animal = it.animal,
            distance = it.distance,
            location = it.location,
            hours = it.hours,
            img = it.img.orEmpty(),
        )
    }


    fun biodiversityItemToBiodiversity(data: List<BiodiversityItem>): List<Biodiversity> =
        data.map {
            Biodiversity(
                id = it.id,
                name = it.name,
                type = it.type,
                location = it.location,
                img = it.img.orEmpty(),
            )
        }

    fun plantItemToPlant(data: List<PlantItem>): List<Plant> = data.map {
        Plant(
            id = it.id,
            name = it.name,
            latin = it.latin,
        )
    }

    fun orderItemToOrder(data: List<OrderItem>): List<Order> = data.map {
        Order(
            id = it.id,
            geositeName = it.geositeName,
            geositeImage = it.geositeImage.orEmpty(),
            tourGuideName = it.tourGuideName,
            bookingDate = it.bookingDate,
            bookingTime = it.bookingTime,
            tourDate = it.tourDate,
            phoneNumber = it.phoneNumber,
            program = it.program,
            status = it.status,
            instance = it.instance,
        )
    }

    fun reportItemToReport(data: List<ReportItem>): List<Report> = data.map {
        Report(
            id = it.id,
            category = it.category,
            name = it.name,
            shortDesc = it.shortDesc,
            place = it.place,
            photo = it.photo.orEmpty(),
            status = it.status,
        )
    }
}