package com.ars.zazil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ars.zazil.View.BottomBar
import com.ars.zazil.View.Login.loginvista
import com.ars.zazil.View.PantallaRegistro
import com.ars.zazil.View.Pantallas
import com.ars.zazil.View.Principal
import com.ars.zazil.View.Sidebar
import com.ars.zazil.View.TopBar
import com.ars.zazil.View.carrito
import com.ars.zazil.View.pagos
import com.ars.zazil.View.preguntas
import com.ars.zazil.ui.theme.ZazilTheme
import com.ars.zazil.ui.theme.naranja
import com.ars.zazil.viewmodel.ProductoAppVM

class MainActivity : ComponentActivity() {
    //ViewModel
    private val productoAppVM: ProductoAppVM by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZazilTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                Sidebar(navController = navController, drawerState = drawerState) {
                    Contenido(productoAppVM,drawerState,navController = navController)
                }

            }
        }
    }
}

@Composable
fun Contenido(
    productoAppVM: ProductoAppVM,
    drawerState: DrawerState,
    navController: NavHostController
){
    Scaffold (
        topBar = {
            Column {
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = naranja)
                )
                TopBar(drawerState,navController, modifier = Modifier)
            }
        },
        bottomBar = { Column {
            BottomBar(navController,modifier = Modifier)
            Box (modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .background(color = naranja)
            )
        }
        }
    ) { innerPadding ->
        AppNavHost(productoAppVM,navController,modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    productoAppVM: ProductoAppVM,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination = Pantallas.RUTA_PRINCIPAL,

    ){

        composable(Pantallas.RUTA_PRINCIPAL) {
            Principal(productoAppVM,modifier)
        }
        composable(Pantallas.RUTA_LOGIN) {
            loginvista()
        }
        composable(Pantallas.RUTA_REGISTRO) {
            PantallaRegistro()
        }

        composable(Pantallas.RUTA_CARRITO){
            carrito()
        }
        composable(Pantallas.RUTA_PREGUNTAS) {
            preguntas(modifier)
        }
        composable(Pantallas.RUTA_PAGOS) {
            pagos(modifier)
        }
    }
}
