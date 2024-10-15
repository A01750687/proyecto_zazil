package com.ars.zazil.View

import android.content.Context
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ars.zazil.Viewmodel.LoginVM
import kotlinx.coroutines.launch

@Composable
fun Sidebar(
    context: Context,
    loginVM: LoginVM,
    navController: NavController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

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
                            coroutineScope.launch {
                                if (item is ItemSidebar.Logout) {
                                    navController.navigate(Pantallas.RUTA_INICIO) {
                                        popUpTo(0)
                                        loginVM.delToken(context)
                                    }
                                    drawerState.close()
                                } else {
                                    navController.navigate(item.ruta)
                                }
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