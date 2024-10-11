package com.ars.zazil.View

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ars.zazil.Viewmodel.LoginVM

@Composable
fun Sidebar(
    loginVM: LoginVM,
    navController: NavController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(180.dp)) {
                ItemSidebar.listaItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(item.icono, contentDescription = null)
                        },
                        label = { Text(text = item.titulo) },
                        selected = false,
                        onClick = {
                            if (item is ItemSidebar.Logout) {
                                navController.navigate(Pantallas.RUTA_INICIO) {
                                    popUpTo(0)
                                    loginVM.setEstadoLogin(false)
                                }
                            }
                            else {
                                navController.navigate(item.ruta)
                            }
                        }
                    )
                }
            }
        }
    ) {
        contenido()
    }
}