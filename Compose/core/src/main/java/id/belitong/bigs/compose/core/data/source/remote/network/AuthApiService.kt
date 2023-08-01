package id.belitong.bigs.compose.core.data.source.remote.network

import id.belitong.bigs.compose.core.data.source.remote.response.LoginResponse
import id.belitong.bigs.compose.core.data.source.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse
}