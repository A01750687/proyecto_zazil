package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.zazil.Model.LoginState
import com.ars.zazil.Model.Pedido
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.Model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * LoginVM
 * Esta clase es un ViewModel que maneja el estado de la autenticación (login) y el registro de usuarios en la aplicación.
 * Se utiliza `MutableStateFlow` para controlar los estados de login, registro, información de usuario, y pedidos.
 */
class LoginVM: ViewModel() {

    private val servicioRemoto = ServicioRemoto()

    // Logueado / Autenticado para permitir el acceso
    private val _estadoLogin = MutableStateFlow(false)
    val estadoLogin: StateFlow<Boolean> = _estadoLogin

    // Estado de registro
    private val _estadoRegistro = MutableStateFlow(Pair(false,""))
    val estadoRegistro: StateFlow<Pair<Boolean,String>> = _estadoRegistro

    // Estado de login con Objeto LoginState
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // Usuario con el que se loguea
    private val _usuarioState = MutableStateFlow(Usuario())
    val usuarioState: StateFlow<Usuario> = _usuarioState

    // Lista de pedidos pasados
    private val _pedidosState = MutableStateFlow(listOf<Pedido>())
    val pedidosState: StateFlow<List<Pedido>> = _pedidosState

    // Estado Edicion Perfil
    private val _estadoEditar = MutableStateFlow(false)
    val estadoEditar: StateFlow<Boolean> = _estadoEditar

    /**
     * login
     * Realiza la autenticación del usuario a través del servicio remoto.
     * Actualiza el estado de login y retorna un valor booleano indicando el éxito.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Boolean Resultado del proceso de login (true si es exitoso, false en caso contrario).
     */
    fun login(email: String, password: String): Boolean {
        viewModelScope.launch {
            val response = servicioRemoto.loginWeb(_loginState,email, password)
            _estadoLogin.value = response
        }
        return _estadoLogin.value
    }

    /**
     * registro
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
    fun registro(nombre: String, direccion: String, edad: Int, email: String, contrasena: String,genero: String){
        viewModelScope.launch {
            val response = servicioRemoto.registroWeb(nombre, direccion, edad, email, contrasena,genero)
            _estadoRegistro.value = response
        }
    }

    /**
     * descargarInfoUsuario
     * Descarga la información del usuario actual desde el servicio remoto.
     * Actualiza el estado del usuario con la información descargada.
     */
    fun descargarInfoUsuario(){
        viewModelScope.launch {
            val response = servicioRemoto.descargarInfoUsuario()
            _usuarioState.value = response
        }
    }

    fun editarUsuario(
        nombre: String,
        direccion: String,
        edad: Int,
        email: String,
        numero: String,
        contrasena: String
    ){
        viewModelScope.launch {
            val response = servicioRemoto.editarPerfil(
                nombre,
                direccion,
                edad,
                email,
                numero,
                contrasena
            )
            _estadoEditar.value = response
        }
    }

    /**
     * descargarPedidos
     * Descarga la lista de pedidos realizados por el usuario actual desde el servicio remoto.
     * Actualiza el estado de pedidos con la lista descargada.
     */
    fun descargarPedidos(){
        viewModelScope.launch {
            val response = servicioRemoto.descargarPedidos()
            _pedidosState.value = response
        }
    }

    /**
     * setEstadoLogin
     * Establece manualmente el estado de login utilizado para cambiar el estado de autenticación del usuario.
     *
     * @param estado Boolean Estado de autenticación (true si el usuario está autenticado, false si no lo está).
     */
    fun setEstadoLogin(estado: Boolean){
        _estadoLogin.value = estado
    }
}

