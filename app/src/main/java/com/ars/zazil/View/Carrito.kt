package com.ars.zazil.View


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ProductoCarrito
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.R
import com.ars.zazil.Viewmodel.CarritoVM
import com.ars.zazil.ui.theme.rosa

@Composable
fun Carrito(carritoViewModel: CarritoVM,modifier: Modifier = Modifier) {

    // Obtener los productos del Carrito usando State
    val productos = carritoViewModel.obtenerProductos().collectAsState()

    // Estado del Scroll
    val scrollState = rememberScrollState()

    // Calcular el total de la compra
    val total = productos.value.sumOf{ it.producto.precio * it.cantidad }

    //Mostrar la informacion de pago
    val mostrarDatosPago = remember { mutableStateOf(false) }

    // Mostar ventana de donacion
    val mostrarDonacion = remember { mutableStateOf(false) }

    // Contenedor principal de contenido
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 72.dp) // Espacio para los botones en la parte inferior
            .verticalScroll(scrollState)
    ) {
        // Contenedor de la parte superior
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            // Título del Carrito
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Carrito de compras",
                style = MaterialTheme.typography.headlineLarge
            )

            // Botón para seguir comprando
            TextButton(
                onClick = { /* Acción para seguir comprando */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Seguir comprando")
            }

            // Cabecera de la tabla
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Productos", modifier = Modifier.weight(5f))
                Text(text = "Total", modifier = Modifier.weight(1f))
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,     // Grosor de la línea
                color = Color.Gray  // Color de la línea divisoria
            )

            // Mostrar los productos del Carrito usando LazyColumn
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ocupa el espacio restante
            ) {
                items(productos.value) { producto ->
                    ProductoItem(producto, carritoViewModel)
                }
            }
        }

        Row{
            // Mostrar el total
            Text(
                text = "Total Final: $",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .weight(1f)
            )
            Text(
                text = String.format("%.2f", total),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .weight(1f)
            )
        }

        // Contenedor de botones en la parte inferior
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón "Continuar compra"
            IconButton(
                onClick = { carritoViewModel.limpiarCarrito() },
                modifier = Modifier
                    .size(200.dp)
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.paypalimg),
                    contentDescription = "Continuar compra"
                )
            }


            // Otro botón (ejemplo)
            IconButton(
                onClick = { mostrarDatosPago.value = true },
                modifier = Modifier
                    .size(200.dp)
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tb),
                    contentDescription = "Otro botón"
                )
            }

            // Otro botón (ejemplo)
            IconButton(
                onClick = { mostrarDatosPago.value= true },
                modifier = Modifier
                    .size(200.dp)
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dep),
                    contentDescription = "Otro botón"
                )
            }
        }
    }
    if (mostrarDatosPago.value) {
        DatosPago(
            onDismiss = {
                mostrarDatosPago.value = false
                carritoViewModel.limpiarCarrito()
            }
        )
    }

    if (mostrarDonacion.value) {
        Donacion(
            onDismiss = {
                mostrarDonacion.value = false
            }
        )
    }

    // Contenedor principal
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Botón flotante de Carrito, posicionado en la parte inferior derecha
        FloatingActionButton(
            onClick = { mostrarDonacion.value = true },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFD22973) // Color del botón flotante
        ) {
            Text(text = "Donar", color = Color.White)
        }
    }

}

@Composable
fun DatosPago(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = "Datos de la cuenta a pagar",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Número de cuenta:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "1096319621",
                    color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cuenta CLABE:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(
                    text = "072180010963196216",
                    color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Nombre del titular:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(
                    text = "Fundación Todas Brillamos A.C.",
                    color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Banco:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(
                    text = "Banorte",
                    color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Concepto:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(
                    text = "Cuota de Recuperación + tu nombre",
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "¡Favor de mandar tu comprobante" +
                            "\nde la tranferencia/depósito por" +
                            "\nmensaje al siguiente número!",
                    textAlign = TextAlign.Center,
                    color = rosa)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "+52 56 2808 3883",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun Donacion(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card (
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.size(width = 350.dp, height = 400.dp)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "¡Gracias por su interés al donar!",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    Text(
                        text = "Por favor, ayúdenos con los siguientes datos",
                        color = rosa
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    Row {
                        Text(
                            text = "Nombre:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(top = 13.dp)
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxWidth()
                                .height(50.dp)
                        )
                    }
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    Row {
                        Text(
                            text = "CURP:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(top = 13.dp)
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxWidth()
                                .height(50.dp)
                        )
                    }
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    Row {
                        Text(
                            text = "Cantidad a donar:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1.4f)
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxWidth()
                                .height(50.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            leadingIcon = {
                                Text(text = "$")
                            }
                        )
                    }
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item{
                    Text(
                        text = "Pagar con:",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    IconButton(
                        onClick = { /*Pagar donación con paypal*/ },
                        modifier = Modifier.size(150.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.paypalimg),
                            contentDescription = "Continuar compra"
                        )
                    }
                }
                item {
                    Row {
                        Text(
                            text = "NOTA:",
                            color = rosa,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "En caso de querer realizar una donación por medio" +
                                    " de transferencia o depósito, por favor, siga las" +
                                    " instrucciones mostradas en los botones al cerrar" +
                                    " esta ventana y proporcione los mismos datos que" +
                                    " en esta se piden por medio de un mensaje al número" +
                                    " indicado, gracias.",
                            color = Color.Black,
                            modifier = Modifier.weight(4f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoItem(producto: ProductoCarrito, carritoViewModel: CarritoVM) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        val context = LocalContext.current
        // Imagen del producto
        Image(
            painter = rememberAsyncImagePainter(model = ServicioRemoto.URL + producto.producto.imagen),
            contentDescription = producto.producto.nombre,
            modifier = Modifier.size(64.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre y precio del producto
        Column(modifier = Modifier.weight(4f)) {
            Text(text = producto.producto.nombre, style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Cantidad: ", style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = {
                    carritoViewModel.disminuirCantidad(producto)
                }) {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircle,
                        contentDescription = "Remove"
                    )
                }
                Text(text = "${producto.cantidad}",style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = {
                    carritoViewModel.aumentarCantidad(producto)
                    if (producto.cantidad == producto.producto.stock) {
                        Toast.makeText(context, "No hay más productos disponibles", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Add"
                    )
                }
            }
        }
        IconButton(modifier = Modifier.align(Alignment.CenterVertically),onClick = { carritoViewModel.eliminarProducto(producto) }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar")
        }
        // Precio total por cantidad
        Text(
            text = "$" + String.format("%.2f", producto.producto.precio * producto.cantidad),
            modifier = Modifier
                .weight(1.5f)
                .align(Alignment.CenterVertically)
        )
    }
}

