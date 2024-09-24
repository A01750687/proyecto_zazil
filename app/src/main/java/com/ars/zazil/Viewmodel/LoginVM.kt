package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginVM: ViewModel() {



    // Logueado / Autenticado
    private val _estadoLogin = MutableStateFlow(false)
    val estadoLogin: StateFlow<Boolean> = _estadoLogin

    fun loginWeb(){
        viewModelScope.launch {

        }
    }

    fun setEstadoLogin(estado: Boolean){
        _estadoLogin.value = estado
    }

}