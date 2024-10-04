package com.ars.zazil.Model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Call

interface AuthService {

    @POST("registerApp/")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @POST("api-token-auth/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("")
    suspend fun descargarPedidos():List<Pedido>

    @GET("JSONItem/{id}")
    suspend fun descargarProducto(@Path("id") id: String):ProductoApp

    @GET("JSONlistadoItems/")
    suspend fun descargarListaProducto(): List<ProductoApp>
}

data class Usuario(
    val nombre: String = "",
    val email: String = "",
    val direccion: String = "",
    val numero: String = "",
    val edad: Int? = null,
)

data class Pedido(
    val estado: Boolean,
    val items: List<ProductoCarrito>
)

data class RegisterRequest(
    val nombre: String = "",
    val direccion: String = "",
    val edad: Int? = null,
    val email: String = "",
    val contrasena: String = "",
    val genero: String = ""
)
data class RegisterResponse(
    val message: String,
    val error: String?
)

data class LoginRequest(
    val username: String,
    val password: String
)
data class TokenResponse(
    val token: String,
    val userId: Int,
)
