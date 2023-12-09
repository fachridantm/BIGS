package id.belitong.bigs.compose.core.data.source.remote.network

import id.belitong.bigs.compose.core.data.source.remote.response.OrderItem
import id.belitong.bigs.compose.core.data.source.remote.response.OrderResponse
import id.belitong.bigs.compose.core.data.source.remote.response.ReportItem
import id.belitong.bigs.compose.core.data.source.remote.response.ReportResponse
import retrofit2.http.GET

interface MockApiService {
    @GET("orders")
    suspend fun getOrders(): List<OrderItem>

    @GET("reports")
    suspend fun getReports(): List<ReportItem>
}