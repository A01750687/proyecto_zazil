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

    fun login(email: String, password: String): Boolean {
        viewModelScope.launch {
            val response = servicioRemoto.loginWeb(_loginState,email, password)
            _estadoLogin.value = response.first
            _usuarioState.value.id = response.second
        }
        return _estadoLogin.value
    }

    fun registro(nombre: String, direccion: String, edad: Int, email: String, contrasena: String,genero: String){
        viewModelScope.launch {
            val response = servicioRemoto.registroWeb(nombre, direccion, edad, email, contrasena,genero)
            _estadoRegistro.value = response
        }
    }

    fun descargarInfoUsuario(){
        viewModelScope.launch {
            val response = servicioRemoto.descargarInfoUsuario(_usuarioState.value.id)
            _usuarioState.value = response
        }
    }

    fun setEstadoLogin(estado: Boolean){
        _estadoLogin.value = estado
    }
}

