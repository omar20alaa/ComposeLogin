package app.compose_login.network.repository.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("accessToken") val accessTokenVerify: String
)