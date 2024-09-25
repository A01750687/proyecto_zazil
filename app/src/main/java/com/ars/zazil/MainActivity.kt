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
import androidx .compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ars.zazil.View.BottomBar
import com.ars.zazil.View.ContactUsPage
import com.ars.zazil.View.Inicio
import com.ars.zazil.View.Login.IniciarSesion
import com.ars.zazil.View.Pantallas
import com.ars.zazil.View.Principal
import com.ars.zazil.View.ProductInDetails
import com.ars.zazil.View.Registro
import com.ars.zazil.View.Sidebar
import com.ars.zazil.View.TopBar
import com.ars.zazil.View.carrito
import com.ars.zazil.View.pagos
import com.ars.zazil.View.preguntas
import com.ars.zazil.ui.theme.ZazilTheme
import com.ars.zazil.ui.theme.naranja
import com.ars.zazil.Viewmodel.LoginVM
import com.ars.zazil.Viewmodel.ProductoAppVM

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
    loginVM: LoginVM = viewModel(),
    navController: NavHostController
){

    val loginEstado = loginVM.estadoLogin.collectAsState()

    val abrirCalendario = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            if(loginEstado.value){
                Column {
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = naranja)
                    )
                    TopBar(abrirCalendario, drawerState,navController, modifier = Modifier)
                }
            }
        },
        bottomBar = {
            if(loginEstado.value){
                Column {
                    BottomBar(navController,modifier = Modifier)
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .background(color = naranja)
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(abrirCalendario,productoAppVM,loginVM,navController,modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    abrirCalendario: MutableState<Boolean>,
    productoAppVM: ProductoAppVM,
    loginVM: LoginVM,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination = Pantallas.RUTA_INICIO,
    ){
        composable(Pantallas.RUTA_INICIO){
            Inicio(navController)
        }
        composable(Pantallas.RUTA_CREARCUENTA){
            Registro(navController)
        }
        composable(Pantallas.RUTA_INICIO_SESION){
            IniciarSesion(loginVM,navController)
        }

        composable(Pantallas.RUTA_PRINCIPAL) {
            Principal(abrirCalendario,navController,productoAppVM,modifier)
        }
        composable(Pantallas.RUTA_DETALLE + "/{id}") {
            val id = it.arguments?.getString("id") ?: "0"
            ProductInDetails(productoAppVM,id,modifier)
        }

        composable(Pantallas.RUTA_CONTACTO) {
            ContactUsPage(modifier = Modifier)
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
