package com.ars.zazil.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ProductoCarrito
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.R
import com.ars.zazil.Viewmodel.CarritoVM

@Composable
fun Carrito(carritoViewModel: CarritoVM,modifier: Modifier = Modifier) {

    // Obtener los productos del Carrito usando State
    val productos = carritoViewModel.obtenerProductos().collectAsState()

    // Estado del Scroll
    val scrollState = rememberScrollState()

    // Calcular el total de la compra
    val total = productos.value.sumOf{ it.producto.precio * it.cantidad }

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


    // Contenedor principal
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Botón flotante de Carrito, posicionado en la parte inferior derecha
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
fun ProductoItem(producto: ProductoCarrito, carritoViewModel: CarritoVM) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
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
            modifier = Modifier.weight(1.5f).align(Alignment.CenterVertically)
        )
    }
}

