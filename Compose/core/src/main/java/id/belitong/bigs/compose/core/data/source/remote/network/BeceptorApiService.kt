package id.belitong.bigs.compose.core.data.source.remote.network

import id.belitong.bigs.compose.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.compose.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.compose.core.data.source.remote.response.PlantResponse
import retrofit2.http.GET

interface BeceptorApiService {
    @GET("geositeItems")
    suspend fun getGeosites(): List<GeositeItem>

    @GET("biodiversities")
    suspend fun getBiodiversities(): List<BiodiversityItem>

    @GET("plants")
    suspend fun getPlant(): PlantResponse
}