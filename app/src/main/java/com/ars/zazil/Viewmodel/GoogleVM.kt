package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GoogleVM : ViewModel() {

    //Auth de firebase
    private val auth:FirebaseAuth = Firebase.auth

    //Estado login /autenticacion
    private val _estadoLogin = MutableStateFlow(false)
    val estadoLogin: StateFlow<Boolean> = _estadoLogin

    fun setEstadoLogin(nuevoEstado: Boolean) {
        _estadoLogin.value = nuevoEstado
    }

    fun hacerlogout () {
        auth.signOut()
        _estadoLogin.value = false
    }
    fun hacerLoginGoogle(credencial: AuthCredential, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credencial)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println("Login con Google, EXITOSO")
                            home()
                        }
                    }
                    .addOnFailureListener {
                        println("ERROR al hacer login con Google")
                    }
            } catch (e: Exception) {
                println("EXCEPCIÓN al hacer login: ${e.localizedMessage}")
            }
        }
}