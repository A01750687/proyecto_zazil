package com.ars.zazil.View

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ProductoCarrito
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.Viewmodel.CarritoVM
import com.ars.zazil.Viewmodel.ProductoAppVM

// Preview con medidas de un celular
@Composable
fun ProductInDetails(
    carritoViewModel: CarritoVM,
    productoAppVM: ProductoAppVM,
    id: String,
    modifier: Modifier = Modifier
) {

    val estado = productoAppVM.estado.collectAsState()
    productoAppVM.descargarProducto(id)

    val mContext = LocalContext.current

    val scrollState = rememberScrollState()

    var cantidad by remember { mutableStateOf(1) }

    Column(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del producto
        Image(
            painter = rememberAsyncImagePainter(ServicioRemoto.URL + estado.value.imagen),
            contentDescription = "Producto",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp)
        )

        // Precio del producto
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                    append("Precio: ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Magenta,
                        fontSize = 16.sp
                    )
                ) {
                    append("${estado.value.precio}")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del producto
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                    append(estado.value.nombre)
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE47B4C),
                        fontSize = 18.sp
                    )
                ) {
                    append(estado.value.categoria)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cantidad
        Text(
            text = "Cantidad",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            fontSize = 18.sp,
            color = Color.Black
        )

        // Botones de cantidad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {

            IconButton(onClick = {
                if (cantidad > 0 && cantidad-1 != 0) {
                    cantidad--
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.RemoveCircle,
                    contentDescription = "Remove"
                )
            }

            Text(
                text = "$cantidad", // Cantidad seleccionada
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 18.sp
            )

            IconButton(onClick = {
                val productoEnCarrito = carritoViewModel.obtenerProducto(estado.value.nombre)

                if (productoEnCarrito == null) {
                    // El producto no está en el carrito, puedes añadirlo si es necesario
                    if (cantidad > 0 && cantidad < estado.value.stock) {
                        cantidad++
                    } else {
                        Toast.makeText(mContext, "No hay más productos disponibles", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // El producto ya está en el carrito, verifica su cantidad
                    if (cantidad > 0 && (cantidad + productoEnCarrito.cantidad) < estado.value.stock) {
                        cantidad++
                    } else {
                        Toast.makeText(mContext, "No hay más productos disponibles", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add"
                )
            }


        }


        ElevatedButton(
            onClick = {
                val producto = ProductoCarrito(estado.value, cantidad)
                val resultado = carritoViewModel.agregarProducto(producto)
                if (resultado) {
                    Toast.makeText(mContext,"Producto añadido al carrito", Toast.LENGTH_SHORT).show()}
                else{
                    Toast.makeText(mContext,"No se pudo agregar el producto, existencias insuficientes", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        { Text(text = "Añadir al carro") }


        Spacer(modifier = Modifier.height(16.dp))

        // Descripción del producto
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    ) {
                        append("• Toalla reutilizable para flujo")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE47B4C),
                            fontSize = 16.sp
                        )
                    ) {
                        append(" moderado")
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("• Alta absorción, impermeable, mantiene la piel fresca y libre de olores brindando una experiencia más cómoda")
                    }
                },
                fontSize = 16.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    ) {
                        append("• Almohadilla interior transpirable adecuada para un flujo")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE47B4C),
                            fontSize = 18.sp
                        )
                    ) {
                        append(" moderado")
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("• Broches de plástico en alas mejorando la fijación en la ropa interior aumentando la seguridad")
                    }
                },
                fontSize = 16.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("• Lavable y reutilizable, fácil de limpiar y secar")
                    }
                },
                fontSize = 16.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("• Hipoalergénica")
                    }
                },
                fontSize = 16.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    ) {
                        append("• Con una medida de")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE47B4C),
                            fontSize = 16.sp
                        )
                    ) {
                        append(" 27 x 7 cm")
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("• Hecha a mano con telas de algodón")
                    }
                },
                fontSize = 16.sp
            )
        }
    }
}