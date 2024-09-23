package com.ars.zazil.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemSidebar(
    val icono: ImageVector,
    val titulo: String,
    val ruta: String
) {
    companion object{
        var listaItems = listOf(Perfil,PedidosPasados,Logout)
    }

    private data object Perfil: ItemSidebar(
        Icons.Outlined.AccountCircle,
        "Mi Perfil",
        ""
    )
    private data object PedidosPasados: ItemSidebar(
        Icons.Outlined.Refresh,
        "Mis Pedidos pasados",
        ""
    )
    private data object Logout: ItemSidebar(
        Icons.Outlined.Close,
        "Cerrar Sesión",
        ""
    )
}