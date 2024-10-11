package com.ars.zazil.Model


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Servicio del servidor
interface AuthService {

    @POST("registerApp/")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @POST("api-token-auth/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>
    @POST("editarP/")
    suspend fun editarPerfil(@Body editarUsuario: EditarUsuario): Response<RegisterResponse>

    @GET("JSONItem/{id}")
    suspend fun descargarProducto(@Path("id") id: String):ProductoApp

    @GET("JSONlistadoItems/")
    suspend fun descargarListaProducto(): List<ProductoApp>

    @GET("JSONinfoUser/")
    suspend fun descargarInfoUsuario():Usuario

    @GET("JSONpedidosPasados/")
    suspend fun descargarPedidos():List<Pedido>
}

// Clases de datos usadas para la respuesta del servidor
data class EditarUsuario(
    val nombre:String = "",
    var direccion:String = "",
    var edad:Int = 0,
    var email:String = "",
    var phone_number:String = "",
    var contrasena:String = "",
)

data class Usuario(
    val nombre: String = "",
    val email: String = "",
    val direccion: String = "",
    val numero: String = "",
    val edad: Int = 0,
)

data class Pedido(
    val fecha: String,
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
    val userId: String,
)
