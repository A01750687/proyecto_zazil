package com.ars.zazil.Model


import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Servicio Remoto
 * Establece conexión con el servidor y manda las peticiones.
 *
 */

class ServicioRemoto {

    // Url para servicio web y token para autenticación de usuario
    companion object {
        const val URL = "http://192.168.23.174:8000/"
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

    /**
     * loginWeb
     * Envia las credenciales de una cuenta y permite el paso o da feedback al usuario
     * del porque no pudo realizar el login
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
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

    /**
     * registroWeb
     * Registra un nuevo usuario en el sistema mediante el servicio remoto.
     * Actualiza el estado del registro con un valor booleano (éxito/fallo) y un mensaje.
     *
     * @param nombre Nombre del usuario.
     * @param direccion Dirección del usuario.
     * @param edad Edad del usuario.
     * @param email Correo electrónico del usuario.
     * @param contrasena Contraseña del usuario.
     * @param genero Género del usuario.
     */
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
            if (response.isSuccessful) {
                val registerResponse = response.body()
                val message = registerResponse?.message ?: "Registro exitoso"
                Pair(true, message)
            } else {
                Pair(false, "Registro fallido: ${response.errorBody() ?: "Error desconocido"}")
            }
        } catch (e: Exception) {
            Pair(false, "Error: ${e.message}")
        }
    }

    /**
     * editarPerfil
     * Con el usuario logueado se realiza una modificación al parametro que el usuario
     * haya modificado y da return si se realizaron cambios
     *
     * @param nombre Nombre del usuario.
     * @param direccion Dirección del usuario.
     * @param edad Edad del usuario.
     * @param email Correo electrónico del usuario.
     * @param contrasena Contraseña del usuario.
     * @param genero Género del usuario.
     */
    suspend fun editarPerfil(
        nombre: String,
        direccion: String,
        edad: Int,
        email: String,
        numero: String,
        contrasena: String
    ):Boolean{
        return try {
            val response = authService.editarPerfil(
                EditarUsuario(nombre,direccion,edad,email,numero,contrasena)
            )
            if (response.isSuccessful) {
                return true
            } else {
                return false
            }

        } catch (e: Exception){
            return false
        }
    }

    /**
     * descargarProducto
     * Descarga la información de un producto específico desde un servicio remoto.
     * @param id Identificador del producto a descargar.
     */
    suspend fun descargarProducto(id: String): ProductoApp {
        return try {
            authService.descargarProducto(id)
        } catch (e: Exception) {
            ProductoApp() // Devuelve un objeto vacío en caso de error
        }
    }

    /**
     * descargarListaProducto
     * Descarga la lista completa de productos desde el servicio remoto.
     * Almacena la lista en _estadoLista y _estadoFiltrado para su uso en la aplicación.
     */
    suspend fun descargarlistaProducto(): List<ProductoApp> {
        return try {
            authService.descargarListaProducto()
        } catch (e: Exception) {
            emptyList() // Devuelve una lista vacía en caso de error
        }
    }

    /**
     * descargarInfoUsuario
     * Descarga la información del usuario actual desde el servicio remoto.
     * Actualiza el estado del usuario con la información descargada.
     */
    suspend fun descargarInfoUsuario():Usuario{
        return try {
            authService.descargarInfoUsuario()
        } catch (e: Exception) {
            Usuario() // Devuelve un Usuario vacio
        }
    }

    /**
     * descargarPedidos
     * Descarga la lista de pedidos realizados por el usuario actual desde el servicio remoto.
     * Actualiza el estado de pedidos con la lista descargada.
     */
    suspend fun descargarPedidos():List<Pedido>{
        return try {
            authService.descargarPedidos()
        } catch (e: Exception) {
            emptyList() // Devuelve un Usuario vacio
        }
    }

    /**
     * Agrega en el encabezado de las peticiones el token recibido en login
     */
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
