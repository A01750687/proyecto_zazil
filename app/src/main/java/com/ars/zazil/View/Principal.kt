package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.R
import com.ars.zazil.ui.theme.fondo
import com.ars.zazil.Viewmodel.ProductoAppVM
import com.mags.pruebas.View.App
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun Principal(
    abrirCalendario: MutableState<Boolean>,
    navController: NavHostController,
    productoAppVM: ProductoAppVM,
    modifier: Modifier = Modifier
) {

    val estadoLista = productoAppVM.estadoLista.collectAsState()

    productoAppVM.descargarListaProducto()

    if(abrirCalendario.value){
        PopupCalendario {
            abrirCalendario.value = false
        }
    }
    Column (modifier = modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Spacer(modifier = Modifier.height(16.dp))
        FilBus()
        Productos(navController,estadoLista,modifier)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    abrirCalendario: MutableState<Boolean>,
    drawerState: DrawerState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val pilaNavegacion by navController.currentBackStackEntryAsState()
    val pantallaActual = pilaNavegacion?.destination

    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(
                text = "Zazil",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
                    .clickable {
                        if (Pantallas.RUTA_PRINCIPAL != pantallaActual?.route) {
                            navController.navigate(Pantallas.RUTA_PRINCIPAL)
                        }
                    }
            )
        },
        modifier = Modifier.height(70.dp),
        actions = {
            IconButton(onClick = {
                abrirCalendario.value = true
            },
                modifier = Modifier.padding(bottom = 15.dp)) {
                Image(painter = painterResource(id = R.drawable.calendario),
                    contentDescription = "Calendario")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                  scope.launch{
                    drawerState.open()
                }
            },
                modifier = Modifier.padding(bottom = 15.dp)) {
                Icon(painter = painterResource(id = R.drawable.tres),
                    contentDescription = "Lateral")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = fondo
        )
    )
}

@Composable
fun PopupCalendario(
    onDismissRequest: () -> Unit
){
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ){
        Card {
            App()
        }
    }
}

@Composable
fun FilBus() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.weight(1f)){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.filtro),
                    contentDescription = "Filtro")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Filtros",
                fontSize = 18.sp)
        }
        TextField(
            value = "",
            onValueChange = {/*TODO: Búsqueda*/ },
            placeholder = { Text("Buscar...",
                style = TextStyle(fontSize = 14.sp))
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            shape = RoundedCornerShape(30.dp)
        )
    }

}

@Composable
fun Productos(navController: NavHostController,estadoLista: State<List<ProductoApp>>, modifier: Modifier = Modifier) {

    var currentPage by remember { mutableStateOf(0)}
    var productosMostrados by remember { mutableStateOf<List<List<ProductoApp>>>(emptyList()) }

    LaunchedEffect(estadoLista.value) {
        if (estadoLista.value.isNotEmpty()){
            productosMostrados = estadoLista.value.chunked(10)
        }
    }

    if (productosMostrados.isNotEmpty()) {

        val totalPag = productosMostrados.size

        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
            LazyColumn (modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
            ) {
                items(productosMostrados[currentPage].chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowItems.forEach { product ->
                            ProductCard(navController, product)
                        }
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (currentPage > 0) {
                        currentPage--
                    }
                },
                    enabled = currentPage > 0
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Anterior")
                }

                Text(
                    text = "${currentPage + 1}",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )

                IconButton(onClick = {
                    if (currentPage < totalPag - 1) {
                        currentPage++
                    }
                },
                    enabled = currentPage < totalPag - 1
                ) {
                    Icon(imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Siguiente")
                }
            }
        }
    }
    else{
        Text(text = "No existen productos")
    }

}

@Composable
fun ProductCard(navController: NavHostController,product: ProductoApp) {
    Card (
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate(Pantallas.RUTA_DETALLE + "/${product.id}")
            }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("http://10.48.79.109:8000/"+product.imagen),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.nombre,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${product.precio}",
                color = Color.Black,
                fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { /*TODO: Añadir al carrito*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir al carrito",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = fondo,
        modifier = Modifier.height(60.dp)
    ) {

        val pilaNavegacion by navController.currentBackStackEntryAsState()
        val pantallaActual = pilaNavegacion?.destination
        Pantallas.listaPantallas.forEach {pantalla ->

            NavigationBarItem(
                selected = pantalla.ruta == pantallaActual?.route,
                onClick = {
                    navController.navigate(pantalla.ruta){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                },
                icon = {
                    Icon(painter = painterResource(id = pantalla.iconId!!),
                        contentDescription = pantalla.etiqueta
                    )
                }
            )
        }
    }
}