package com.ars.zazil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ars.zazil.Model.Qa
import com.ars.zazil.View.BottomBar
import com.ars.zazil.View.ContactUsPage
import com.ars.zazil.View.EditarP
import com.ars.zazil.View.Inicio
import com.ars.zazil.View.Login.IniciarSesion
import com.ars.zazil.View.Pantallas
import com.ars.zazil.View.Perfil
import com.ars.zazil.View.Principal
import com.ars.zazil.View.ProductInDetails
import com.ars.zazil.View.Registro
import com.ars.zazil.View.Sidebar
import com.ars.zazil.View.TopBar
import com.ars.zazil.View.Carrito
import com.ars.zazil.View.Creditos
import com.ars.zazil.View.detallePregunta
import com.ars.zazil.View.pagos
import com.ars.zazil.View.preguntas
import com.ars.zazil.View.preguntasFrec
import com.ars.zazil.Viewmodel.CarritoVM
import com.ars.zazil.Viewmodel.GoogleVM
import com.ars.zazil.ui.theme.ZazilTheme
import com.ars.zazil.ui.theme.naranja
import com.ars.zazil.Viewmodel.LoginVM
import com.ars.zazil.Viewmodel.ProductoAppVM
import com.mags.pruebas.View.Pedidos_Pasados
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

class MainActivity : ComponentActivity() {

    private val loginVM: LoginVM by viewModels()

    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginVM.inicializarToken(this)
        loginVM.initializePaymentSheet(this,this)
        PaymentConfiguration.init(
            this,
            "pk_test_51QAhEw2K799XBlDvFa90HJL1N9TF96Zc2Lxhap0t3xqORw2E4qwmADNVoIO9O9o4ED775BGs8DamhEnriVxpor5m00fd7MTDQk",
        )
        enableEdgeToEdge()
        setContent {
            ZazilTheme(dynamicColor = false, darkTheme = false) {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                val estadologin = loginVM.estadoLogin.collectAsState()

                if(estadologin.value){
                    Sidebar(this,loginVM = loginVM,navController = navController, drawerState = drawerState) {
                        Contenido(loginVM = loginVM,drawerState = drawerState,navController = navController)
                    }
                }else{
                    Contenido(loginVM = loginVM,drawerState = drawerState,navController = navController)
                }

            }
        }
    }
}

@Composable
fun Contenido(
    carritoViewModel: CarritoVM = viewModel(),
    productoAppVM: ProductoAppVM = viewModel(),
    googleVM: GoogleVM = viewModel(),
    drawerState: DrawerState,
    loginVM: LoginVM,
    navController: NavHostController,
){

    loginVM.getToken()

    val loginEstado = loginVM.estadoLogin.collectAsState()

    val startDestination: String

    if(loginEstado.value){
        startDestination = Pantallas.RUTA_PRINCIPAL
    }else{
        startDestination = Pantallas.RUTA_INICIO
    }

    Scaffold (
        topBar = {
            if(loginEstado.value){
                Column {
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = naranja)
                    )
                    TopBar(drawerState,navController, modifier = Modifier)
                }
            }
        },
        bottomBar = {
            if(loginEstado.value){
                Column {
                    BottomBar(navController,modifier = Modifier)
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(startDestination,googleVM,carritoViewModel,productoAppVM,loginVM,navController,modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    startDestination: String,
    googleVM: GoogleVM,
    carritoViewModel: CarritoVM,
    productoAppVM: ProductoAppVM,
    loginVM: LoginVM,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination = startDestination,
    ){
        composable(Pantallas.RUTA_INICIO){
            Inicio(googleVM,navController)
        }
        composable(Pantallas.RUTA_CREARCUENTA){
            Registro(googleVM,loginVM,navController)
        }
        composable(Pantallas.RUTA_INICIO_SESION){
            IniciarSesion(loginVM,navController)
        }

        composable(Pantallas.RUTA_PERFIL) {
            Perfil(loginVM,navController,modifier)
        }
        composable(Pantallas.RUTA_EDITARPERFIL) {
            EditarP(loginVM,navController,modifier)
        }

        composable(Pantallas.RUTA_PEDIDOS) {
            Pedidos_Pasados(navController,loginVM,modifier)
        }

        composable(Pantallas.RUTA_CREDITOS){
            Creditos(modifier)
        }

        composable(Pantallas.RUTA_PRINCIPAL) {
            Principal(loginVM,navController,carritoViewModel,productoAppVM,modifier)
        }
        composable(Pantallas.RUTA_DETALLE + "/{id}") {
            val id = it.arguments?.getString("id") ?: "0"
            ProductInDetails(carritoViewModel,productoAppVM,id,modifier)
        }

        composable(Pantallas.RUTA_CONTACTO) {
            ContactUsPage(modifier = modifier)
        }
        composable(Pantallas.RUTA_CARRITO){
            Carrito(loginVM,carritoViewModel,modifier)
        }
        composable(Pantallas.RUTA_PREGUNTAS) {
            preguntas(modifier, navController = navController)
        }
        composable(Pantallas.RUTA_PAGOS) {
            pagos(modifier)
        }
        composable("ListaPreguntas") {
            preguntasFrec(modifier, navController = navController)
        }
        // Pasamos la pregunta y la respuesta como argumentos de la ruta
        composable("detalle/{pregunta}/{respuesta}") { backStackEntry ->
            val pregunta = backStackEntry.arguments?.getString("pregunta") ?: ""
            val respuesta = backStackEntry.arguments?.getString("respuesta") ?: ""
            val qa = Qa(pregunta, respuesta)
            detallePregunta(pregunta = qa, navController = navController, modifier = modifier)
        }
    }
}
