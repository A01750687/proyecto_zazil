package com.ars.zazil.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ars.zazil.Model.producto
import com.ars.zazil.R
import com.ars.zazil.Viewmodel.CarritoVM

@Composable
fun carrito() {
    // Obtén el ViewModel
    val carritoViewModel: CarritoVM = viewModel()

    // Obtener los productos del carrito usando State
    val productos = carritoViewModel.obtenerProductos().collectAsState(initial = emptyList())

    // Calcular la suma total de los precios
    val total = productos.value.sumOf { it.precio * it.cantidad }

    // Contenedor principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Contenedor principal de contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp) // Espacio para los botones en la parte inferior
        ) {
            // Contenedor de la parte superior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                // Título del carrito
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
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

                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color.Gray,  // Color de la línea divisoria
                    thickness = 1.dp     // Grosor de la línea
                )

                // Mostrar los productos del carrito usando LazyColumn
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

            Row() {
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
                    .padding(bottom = 16.dp)
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
                    onClick = { carritoViewModel.limpiarCarrito() },
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
                    onClick = { carritoViewModel.limpiarCarrito() },
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

        // Botón flotante de carrito, posicionado en la parte inferior derecha
        FloatingActionButton(
            onClick = { /* Acción al hacer clic */ },
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
fun ProductoItem(producto: producto, carritoViewModel: CarritoVM) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Imagen del producto
        Image(
            painter = painterResource(id = producto.imagen),
            contentDescription = producto.nombre,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre y precio del producto
        Column(modifier = Modifier.weight(4f)) {
            Text(text = producto.nombre, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Cantidad: ${producto.cantidad}", style = MaterialTheme.typography.bodyMedium)
            Row {
                TextButton(onClick = { carritoViewModel.disminuirCantidad(producto) }) {
                    Text(text = "-", color = Color.Red, fontSize = 20.sp)
                }
                TextButton(onClick = { carritoViewModel.aumentarCantidad(producto) }) {
                    Text(text = "+", color = Color.Green, fontSize = 20.sp)
                }
            }
        }

        // Precio total por cantidad
        Text(
            text = "$" + String.format("%.2f", producto.precio * producto.cantidad),
            modifier = Modifier.weight(1.5f)
        )
    }
}

