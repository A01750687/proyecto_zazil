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

    // Url para servicio web y token para autenticación de usuario
    companion object {
        const val URL = "http://10.48.73.189:8000/"
        var token = ""
    }

    // Objeto retrofit con Cliente
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor{token})
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

    // Funcion para login y obteniendo token para autenticación
    suspend fun loginWeb(loginState: MutableStateFlow<LoginState>, email: String, password: String): Boolean {
        loginState.value = LoginState.Loading
        try {
            val response = authService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val tokenResponse = response.body()
                token = tokenResponse?.token ?: ""

                loginState.value = LoginState.Success("")
                return true
            } else {
                if(response.code() == 406){
                    loginState.value = LoginState.Error("Correo no encontrado")
                } else if (response.code() == 401){
                    loginState.value = LoginState.Error("Contraseña incorrecta")
                }
                return false
            }
        } catch (e: Exception) {
            loginState.value = LoginState.Error(e.message ?: "Unknown error")
            return false
        }
    }

    // Registro que devuelve si se logro el registro o no junto con mensaje del servidor
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

    // Descarga el producto y en caso de algún fallo devuelve un producto vacio
    suspend fun descargarProducto(id: String): ProductoApp {
        return try {
            authService.descargarProducto(id)
        } catch (e: Exception) {
            ProductoApp() // Devuelve un objeto vacío en caso de error
        }
    }

    // Descarga la lista de productos y en caso de algún fallo devuelve una lista vacia
    suspend fun descargarlistaProducto(): List<ProductoApp> {
        return try {
            authService.descargarListaProducto()
        } catch (e: Exception) {
            emptyList() // Devuelve una lista vacía en caso de error
        }
    }

    // Descarga los datos del usuario logueado
    suspend fun descargarInfoUsuario():Usuario{
        return try {
            authService.descargarInfoUsuario()
        } catch (e: Exception) {
            Usuario() // Devuelve un Usuario vacio
        }
    }

    // Descarga los pedidos del usuario logueado
    suspend fun descargarPedidos():List<Pedido>{
        return try {
            authService.descargarPedidos()
        } catch (e: Exception) {
            emptyList() // Devuelve un Usuario vacio
        }
    }

    // Agrega en el encabezado el token para autenticación
    class AuthInterceptor(private val tokenProvider: () -> String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = tokenProvider() // Obtiene el token actual dinámicamente
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Token $token")
                .build()

            return chain.proceed(newRequest)
        }
    }
}

// Objeto para el estado del login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data class Error(val message: String) : LoginState()
}
