package app.compose_login.network.repository

import app.compose_login.network.repository.model.LoginRequest
import app.compose_login.network.repository.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>
}