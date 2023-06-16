package id.belitong.bigs.core.data.source.remote.network

import id.belitong.bigs.core.data.source.remote.response.BiodiversityResponse
import id.belitong.bigs.core.data.source.remote.response.GeositeResponse
import retrofit2.http.GET

interface MainApiService {
    @GET("geosites")
    suspend fun getAllGeosites(): GeositeResponse

    @GET("biodiversities")
    suspend fun getAllBiodiversity(): BiodiversityResponse
}