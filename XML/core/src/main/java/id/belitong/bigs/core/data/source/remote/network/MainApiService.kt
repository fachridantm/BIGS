package id.belitong.bigs.core.data.source.remote.network

import id.belitong.bigs.core.data.source.remote.response.BiodiversityItem
import id.belitong.bigs.core.data.source.remote.response.GeositeItem
import id.belitong.bigs.core.data.source.remote.response.OrderItem
import id.belitong.bigs.core.data.source.remote.response.PlantResponse
import id.belitong.bigs.core.data.source.remote.response.ReportItem
import retrofit2.http.GET

interface MainApiService {
    @GET("geosites")
    suspend fun getGeosites(): List<GeositeItem>

    @GET("biodiversities")
    suspend fun getBiodiversities(): List<BiodiversityItem>

    @GET("plant")
    suspend fun getPlant(): PlantResponse

    @GET("orders")
    suspend fun getOrders(): List<OrderItem>

    @GET("reports")
    suspend fun getReports(): List<ReportItem>
}