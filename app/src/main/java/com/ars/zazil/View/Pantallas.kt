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
        var listaPantallas = listOf(Contacto,Carrito,Principal,Preguntas,Pagos)
        const val RUTA_INICIO = "Inicio"
        const val RUTA_CREARCUENTA = "CrearCuenta"
        const val RUTA_INICIO_SESION = "InicioSesion"

        const val RUTA_PERFIL = "Perfil"
        const val RUTA_EDITARPERFIL = "EditarPerfil"
        const val RUTA_PEDIDOS = "Pedidos"

        const val RUTA_PRINCIPAL = "Principal"
        const val RUTA_DETALLE = "Detalle"

        const val RUTA_CONTACTO = "Contacto"
        const val RUTA_CARRITO = "Carrito"
        const val RUTA_PREGUNTAS = "Preguntas"
        const val RUTA_PAGOS = "Pagos"

        const val RUTA_LISTAPREGUNTAS = "ListaPreguntas"
        const val RUTA_DETALLEPREGUNTA = "DetallePregunta"

        const val RUTA_CREDITOS = "Creditos"
    }
    //Pantallas
    private data object Inicio: Pantallas(RUTA_INICIO,"Inicio")
    private data object CrearCuenta: Pantallas(RUTA_CREARCUENTA,"CrearCuenta")
    private data object InicioSesion: Pantallas(RUTA_INICIO_SESION,"InicioSesion")

    private data object Perfil: Pantallas(RUTA_PERFIL, "Perfil")
    private data object EditarPerfil: Pantallas(RUTA_EDITARPERFIL, "EditarPerfil")
    private data object Pedidos: Pantallas(RUTA_PEDIDOS, "Pedidos Pasados")

    private data object Principal: Pantallas(RUTA_PRINCIPAL,"Principal",R.drawable.casa)
    private data object Detalle: Pantallas(RUTA_DETALLE,"Detalle")

    private data object Contacto: Pantallas(RUTA_CONTACTO,"Contacto",R.drawable.telefono)
    private data object Carrito: Pantallas(RUTA_CARRITO,"Carrito", R.drawable.carrito)
    private data object Preguntas: Pantallas(RUTA_PREGUNTAS,"Preguntas", R.drawable.pregunta)
    private data object Pagos: Pantallas(RUTA_PAGOS,"Pago", R.drawable.pago)

    private data object ListaPreguntas: Pantallas(RUTA_LISTAPREGUNTAS,"ListaPreguntas")
    private data object DetallePregunta: Pantallas(RUTA_DETALLEPREGUNTA,"DetallePregunta")

    private data object Creditos: Pantallas(RUTA_CREDITOS,"Creditos")
}
