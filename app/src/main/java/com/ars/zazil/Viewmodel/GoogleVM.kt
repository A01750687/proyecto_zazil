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

/**
 * GoogleVM
 * Este ViewModel gestiona el proceso de autenticación a través de Google utilizando Firebase.
 * Proporciona funciones para iniciar y cerrar sesión, y mantiene el estado de autenticación.
 */
class GoogleVM : ViewModel() {

    //Auth de firebase
    private val auth:FirebaseAuth = Firebase.auth

    //Estado login /autenticacion
    private val _estadoLogin = MutableStateFlow(false)
    val estadoLogin: StateFlow<Boolean> = _estadoLogin

    /**
     * setEstadoLogin
     * Cambia manualmente el estado de autenticación del usuario.
     * @param nuevoEstado Nuevo estado de autenticación (true si el usuario está autenticado, false si no).
     */
    fun setEstadoLogin(nuevoEstado: Boolean) {
        _estadoLogin.value = nuevoEstado
    }

    /**
     * hacerlogout
     * Cierra la sesión del usuario utilizando FirebaseAuth y actualiza el estado de login.
     */
    fun hacerlogout () {
        auth.signOut()
        _estadoLogin.value = false
    }

    /**
     * hacerLoginGoogle
     * Realiza el inicio de sesión en Firebase utilizando las credenciales de Google.
     * Si el login es exitoso, se ejecuta la función `home` para navegar a la página principal.
     *
     * @param credencial Credenciales de autenticación de Google.
     * @param home Función lambda que se ejecuta si el login es exitoso.
     */
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