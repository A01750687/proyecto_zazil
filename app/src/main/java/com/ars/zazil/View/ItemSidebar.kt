package com.ars.zazil.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemSidebar(
    val icono: ImageVector,
    val titulo: String,
    val ruta: String
) {
    companion object{
        var listaItems = listOf(Perfil,PedidosPasados,Creditos,Logout)
    }

    private data object Perfil: ItemSidebar(
        Icons.Outlined.AccountCircle,
        "Mi Perfil",
        Pantallas.RUTA_PERFIL
    )
    private data object PedidosPasados: ItemSidebar(
        Icons.Outlined.Refresh,
        "Mis Pedidos pasados",
        Pantallas.RUTA_PEDIDOS
    )
    data object Logout: ItemSidebar(
        Icons.Outlined.Close,
        "Cerrar Sesión",
        ""
    )
    data object Creditos: ItemSidebar(
        Icons.Outlined.Laptop,
        "Créditos",
        Pantallas.RUTA_CREDITOS
    )

}