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

    // Logueado / Autenticado
    private val _estadoLogin = MutableStateFlow(false)
    val estadoLogin: StateFlow<Boolean> = _estadoLogin

    private val _estadoRegistro = MutableStateFlow(Pair(false,""))
    val estadoRegistro: StateFlow<Pair<Boolean,String>> = _estadoRegistro

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _usuarioState = MutableStateFlow(Usuario())
    val usuarioState: StateFlow<Usuario> = _usuarioState

    private val _pedidosState = MutableStateFlow(listOf<Pedido>())
    val pedidosState: StateFlow<List<Pedido>> = _pedidosState

    fun login(email: String, password: String): Boolean {
        viewModelScope.launch {
            val response = servicioRemoto.loginWeb(_loginState,email, password)
            _estadoLogin.value = response.first
        }
        return _estadoLogin.value
    }
    fun registro(nombre: String, direccion: String, edad: Int, email: String, contrasena: String,genero: String){
        viewModelScope.launch {
            val response = servicioRemoto.registroWeb(nombre, direccion, edad, email, contrasena,genero)
            _estadoRegistro.value = response
        }

    }

    fun setEstadoLogin(estado: Boolean){
        _estadoLogin.value = estado
    }
}

