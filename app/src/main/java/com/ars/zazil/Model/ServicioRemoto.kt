package com.ars.zazil.Model


import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicioRemoto {

    // Url para servicio web
    companion object {
        const val URL = "http://10.48.67.204:8000/"
        var token = ""
    }

    private var usuario = ""

    private val tokenProvider = TokenProvider()

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .build()

        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val authService by lazy {
        retrofit.create(AuthService::class.java)
    }

    suspend fun loginWeb(loginState: MutableStateFlow<LoginState>, email: String, password: String): Pair<Boolean, String> {
        loginState.value = LoginState.Loading
        try {
            val response = authService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val tokenResponse = response.body()

                tokenProvider.setToken(tokenResponse?.token)
                token = tokenResponse?.token ?: ""

                loginState.value = LoginState.Success(usuario)
                return Pair(true, usuario)
            } else {
                loginState.value = LoginState.Error("Login failed")
                return Pair(false, "")
            }
        } catch (e: Exception) {
            loginState.value = LoginState.Error(e.message ?: "Unknown error")
            return Pair(false, "")
        }
    }

    suspend fun registroWeb(
        nombre: String,
        direccion: String,
        edad: Int,
        email: String,
        contrasena: String,
        genero: String
    ): Pair<Boolean, String> {
        return try {
            val response = authService.register(
                RegisterRequest(nombre, direccion, edad, email, contrasena,genero)
            )
            Log.d("RegistroWeb", "Código de estado: ${response.code()}")
            if (response.isSuccessful) {
                val registerResponse = response.body()
                val message = registerResponse?.message ?: "Registro exitoso"
                Log.d("RegistroWeb", "Respuesta del servidor: $registerResponse")
                Pair(true, message)
            } else {
                Log.d("RegistroWeb", "Error en el registro: ${response.errorBody() ?: "Error desconocido"}")
                Pair(false, "Registro fallido: ${response.errorBody() ?: "Error desconocido"}")
            }
        } catch (e: Exception) {
            Pair(false, "Error: ${e.message}")
        }
    }

    suspend fun descargarProducto(id: String): ProductoApp {
        return try {
            authService.descargarProducto(id)
        } catch (e: Exception) {
            ProductoApp() // Devuelve un objeto vacío en caso de error
        }
    }

    suspend fun descargarlistaProducto(): List<ProductoApp> {
        return try {
            Log.d("YANOSE","${tokenProvider.getToken()}")
            authService.descargarListaProducto()
        } catch (e: Exception) {
            emptyList() // Devuelve una lista vacía en caso de error
        }
    }

    suspend fun descargarPedidos():List<Pedido>{
        return emptyList()
    }

    class AuthInterceptor(private val tokenProvider: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Token $tokenProvider")
                .build()

            return chain.proceed(newRequest)
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}
