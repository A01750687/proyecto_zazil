package com.ars.zazil.View

import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.Model.ProductoCarrito
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.R
import com.ars.zazil.Viewmodel.CarritoVM
import com.ars.zazil.Viewmodel.LoginVM
import com.ars.zazil.ui.theme.fondo
import com.ars.zazil.Viewmodel.ProductoAppVM
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun Principal(
    loginVM: LoginVM,
    navController: NavHostController,
    carritoViewModel: CarritoVM,
    productoAppVM: ProductoAppVM,
    modifier: Modifier = Modifier
) {

    val estadoFiltrado = productoAppVM.estadoFiltrado.collectAsState()
    val sinProductosDialog = productoAppVM.sinProductosDialog.collectAsState()

    productoAppVM.descargarListaProducto()
    loginVM.descargarCategoria()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        Spacer(modifier = Modifier.height(16.dp))
        FilBus(loginVM, productoAppVM)
        Productos(carritoViewModel, navController, estadoFiltrado, sinProductosDialog, productoAppVM)
    }
}

@Composable
fun ShowFilterDialog(productoAppVM: ProductoAppVM) {
    AlertDialog(
        onDismissRequest = {
            productoAppVM.setSinProductosDialogFalse()
        },
        title = {
            Text(text = "Sin Resultados")
        },
        text = {
            Text(text = "No hay productos que coincidan con los filtros introducidos.")
        },
        confirmButton = {
            Button(
                onClick = {
                    productoAppVM.setSinProductosDialogFalse()
                }
            ) {
                Text("Aceptar")
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
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
                color = Color.Black,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .clickable {
                        if (Pantallas.RUTA_PRINCIPAL != pantallaActual?.route) {
                            navController.navigate(Pantallas.RUTA_PRINCIPAL) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    }
            )
        },
        modifier = Modifier.height(70.dp),

        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tres),
                    contentDescription = "Lateral"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = fondo
        )
    )
}

@Composable
fun FilBus(loginVM: LoginVM,productoAppVM: ProductoAppVM) {
    var query by remember { mutableStateOf("") }
    var verFiltros by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            IconButton(onClick = {
                verFiltros = true
            }) {
                Image(
                    painter = painterResource(id = R.drawable.filtro),
                    contentDescription = "Filtro"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Filtros",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        TextField(
            value = query,
            onValueChange = {
                query = it
                productoAppVM.searchProducto(query)
            },
            placeholder = {
                Text(
                    "Buscar...",
                    style = TextStyle(fontSize = 14.sp)
                )
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            shape = RoundedCornerShape(30.dp)
        )
    }
    if (verFiltros) {
        Filtros(loginVM,onDismiss = { verFiltros = false }, productoAppVM)
    }
}

@Composable
fun Filtros(loginVM: LoginVM,onDismiss: () -> Unit, productoAppVM: ProductoAppVM) {
    var SelectedCategoria by remember { mutableStateOf("Categoria") }
    var minPrice by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
            ) {
                //Parte superior Filtros y Restablecer
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Filtros",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    TextButton(
                        onClick = {
                            productoAppVM.limpiarLista()

                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        TextButton(
                            onClick = {
                                productoAppVM.limpiarLista()
                                minPrice = ""
                                maxPrice = ""
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Restablecer",
                                color = Color.Blue,
                                style = TextStyle(fontSize = 20.sp)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                //Filtros Y sus opciones
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(8.dp)
                        .height(225.dp)
                ) { //Filtros
                    Column(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(end = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CategoryButton(
                            label = "Categoria",
                            isSelected = SelectedCategoria == "Categoría"
                        ) {
                            SelectedCategoria = "Categoría"
                        }
                        CategoryButton(
                            label = "Tamaño",
                            isSelected = SelectedCategoria == "Tamaño"
                        ) {
                            SelectedCategoria = "Tamaño"
                        }
                        CategoryButton(
                            label = "Precio",
                            isSelected = SelectedCategoria == "Precio"
                        ) {
                            SelectedCategoria = "Precio"
                        }
                    }
                    //Opciones de filtros
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = SelectedCategoria,
                            color = Color.Red,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        val categorias = loginVM.estadoCategoria.collectAsState()
                        when (SelectedCategoria) {
                            "Categoría" -> {
                                LazyColumn {
                                    categorias.value.forEach { category ->
                                        item{
                                            CheckboxItem(category.nombre, productoAppVM)
                                        }
                                    }
                                }
                            }

                            "Tamaño" -> {
                                LazyColumn {
                                    items(
                                        listOf(
                                            "Nocturna",
                                            "Panti",
                                            "Regular",
                                            "Teen"
                                        )
                                    ) { size ->
                                        CheckboxItem(size, productoAppVM)
                                    }
                                }
                            }

                            "Precio" -> {
                                Column {
                                    TextField(
                                        value = minPrice,
                                        onValueChange = { newValue ->
                                            minPrice = newValue
                                            val min = newValue.toDoubleOrNull()
                                            productoAppVM.setMinPrice(min)
                                        },
                                        label = { Text("Min") },
                                        placeholder = { Text("$ MIN") },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                    TextField(
                                        value = maxPrice,
                                        onValueChange = { newValue ->
                                            maxPrice = newValue
                                            val max = newValue.toDoubleOrNull()
                                            productoAppVM.setMaxPrice(max)
                                        },
                                        label = { Text("Max") },
                                        placeholder = { Text("$ MAX") },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                }
                            }

                        }
                        //Aplicar filtro

                    }
                }
                Button(onClick = { productoAppVM.aplicarFiltros()
                    onDismiss()
                }) {
                    Text("Aplicar filtros")
                }
            }
        }
    }
}

@Composable
fun CategoryButton(label: String, isSelected: Boolean = false, onClick: () -> Unit) {
    val backgroundColor =
        if (isSelected) Color(0xFFFFC1E3) else Color.Transparent
    val borderColor =
        if (isSelected) Color(0xFFBF5F82) else Color.Gray

    Text(
        text = label,
        color = if (isSelected) Color(0xFFBF5F82) else Color.Black,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .border(width = 1.dp, color = borderColor)
            .padding(8.dp)
            .clickable { onClick() }
    )
}

@Composable
fun CheckboxItem(label: String, productoAppVM: ProductoAppVM) {
    val checkboxStates by productoAppVM.checkboxStates.collectAsState()
    val isChecked = checkboxStates[label] ?: false

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                productoAppVM.updateCheckboxState(label, checked)
            }
        )
        Text(text = label, fontSize = 14.sp)
    }
}


// Falta el texto de que no sale un producto al buscarlo, porfa no le muevan
@Composable
fun Productos(
    carritoViewModel: CarritoVM,
    navController: NavHostController,
    estadoFiltrado: State<List<ProductoApp>>,
    sinProductosDialog: State<Boolean>,
    productoAppVM: ProductoAppVM,
) {

    var currentPage by remember { mutableStateOf(0) }
    var productosMostrados by remember { mutableStateOf<List<List<ProductoApp>>>(emptyList()) }

    LaunchedEffect(estadoFiltrado.value) {
        if (estadoFiltrado.value.isNotEmpty()) {
            productosMostrados = estadoFiltrado.value.chunked(10)
        }
    }

    if (sinProductosDialog.value) {
        ShowFilterDialog(productoAppVM)
    }

    if (productosMostrados.isNotEmpty()) {

        val totalPag = productosMostrados.size

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(4.dp)
            ) {
                items(productosMostrados[currentPage].chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowItems.forEach { product ->
                            ProductCard(carritoViewModel,navController, product)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (currentPage > 0) {
                            currentPage--
                        }
                    },
                    enabled = currentPage > 0
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Anterior"
                    )
                }

                Text(
                    text = "${currentPage + 1} / $totalPag",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )

                IconButton(
                    onClick = {
                        if (currentPage < totalPag - 1) {
                            currentPage++
                        }
                    },
                    enabled = currentPage < totalPag - 1
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Siguiente"
                    )
                }
            }
        }
    } else {
        Text(text = "No existen productos")
    }

}

@Composable
fun ProductCard(carritoViewModel: CarritoVM, navController: NavHostController, product: ProductoApp) {
    val mContext = LocalContext.current
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate(Pantallas.RUTA_DETALLE + "/${product.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = fondo
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(ServicioRemoto.URL + product.imagen),
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
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            var descuento = ""
            if(product.descuento > 0.0){
                descuento = "DESCUENTO: %${String.format("%.2f",product.descuento)}"
            }
            Text(
                text = descuento,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "Stock:${product.stock} $${String.format("%.2f",product.precio)}",
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(232, 98, 61)),
                onClick = {
                    val producto = ProductoCarrito(product, 1)
                    carritoViewModel.agregarProducto(producto)
                    Toast.makeText(mContext,"Producto añadido al carrito",Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Añadir al Carrito",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = fondo,
        modifier = Modifier.height(85.dp)
    ) {

        val pilaNavegacion by navController.currentBackStackEntryAsState()
        val pantallaActual = pilaNavegacion?.destination
        //print("funciona")
        Pantallas.listaPantallas.forEach { pantalla ->

            NavigationBarItem(
                selected = pantalla.ruta == pantallaActual?.route,
                onClick = {
                    if (pantalla.ruta != pantallaActual?.route) {
                        navController.navigate(pantalla.ruta) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = pantalla.iconId!!),
                        contentDescription = pantalla.etiqueta
                    )
                }
            )
        }
    }
}
