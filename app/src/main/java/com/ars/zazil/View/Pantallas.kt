package com.ars.zazil.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import com.ars.zazil.R

sealed class Pantallas(
    val ruta: String,
    val etiqueta: String,
    val iconId: Int? = null
){
    // Objeto de la clase Pantallas(static)
    companion object{
        var listaPantallas = listOf(Carrito,Preguntas,Pagos)
        const val RUTA_INICIO = "Inicio"
        const val RUTA_CREARCUENTA = "CrearCuenta"
        const val RUTA_INICIO_SESION = "InicioSesion"
        const val RUTA_PRINCIPAL = "Principal"
        const val RUTA_LOGIN = "Login"
        const val RUTA_REGISTRO = "Registro"

        const val RUTA_CARRITO = "Carrito"
        const val RUTA_PREGUNTAS = "Preguntas"
        const val RUTA_PAGOS = "Pagos"
    }
    //Pantallas
    private data object Inicio: Pantallas(RUTA_INICIO,"Inicio")
    private data object CrearCuenta: Pantallas(RUTA_CREARCUENTA,"CrearCuenta")
    private data object InicioSesion: Pantallas(RUTA_INICIO_SESION,"InicioSesion")

    private data object Principal: Pantallas(RUTA_PRINCIPAL,"Principal")
    private data object Login: Pantallas(RUTA_LOGIN,"Login")
    private data object Registro: Pantallas(RUTA_REGISTRO,"Registro")

    private data object Carrito: Pantallas(RUTA_CARRITO,"Carrito", R.drawable.carrito)
    private data object Preguntas: Pantallas(RUTA_PREGUNTAS,"Preguntas", R.drawable.pregunta)
    private data object Pagos: Pantallas(RUTA_PAGOS,"Pago", R.drawable.pago)
}
