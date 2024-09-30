package com.ars.zazil.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun AlertaSomos(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },
        title = { Text("¿Quiénes somos?") },
        text = {
            Text("Zazil es una marca comprometida con\n" +
                    "el bienestar de las mujeres y el cuidado\n" +
                    "del medio ambiente. Su misión es\n" +
                    "proporcionar soluciones innovadoras y\n" +
                    "sostenibles para el período menstrual.\n" +
                    "¿Cómo lo hacen? A través de la creación\n" +
                    "de toallas femeninas reutilizables.\n " +
                    "\n" +
                    "Cambiando el Mundo, un Ciclo a la Vez.")
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Alerta Somos"
            )
        }
    )
}

@Composable
fun AlertaContenido(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },
        title = { Text("Contenido del producto") },
        text = {
            Text("- Toallas Hipoalergénicas. Al estar hechas\n" +
                    "con telas de algodón no ocasionan irritación\n" +
                    "\n" +
                    "- Tiene una vida útil de hasta 5 años\n" +
                    "\n" +
                    "- Se utiliza de 1 a 2 litros de agua para lavarlas\n" +
                    "\n" +
                    "- No causan mal olor por ser transpirables\n" +
                    "\n" +
                    "- Al tirarse a la basura se biodegradan en sustancias orgánicas en 1 a 5 años")
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Alerta Contenido"
            )
        }
    )
}

@Composable
fun AlertaCompra(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },
        title = { Text("¿Cómo comprar?") },
        text = {
            Text("- Selecciona un producto en la página principal.\n" +
                    "\n" +
                    "- Selecciona la cantidad.\n" +
                    "\n" +
                    "- Añade al carrito.\n" +
                    "\n" +
                    "- Puedes seguir añadiendo más productos al carrito o proceder a pagar lo que esté en el carrito.\n" +
                    "\n" +
                    "- Selecciona el metodo de pago entre Paypal, Transferencia bancaria o Depósito")
        },
        icon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Alerta Compra"
            )
        }
    )
}

@Composable
fun AlertaDevolucion(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },
        title = { Text("¿Cómo devolver un producto?") },
        text = {
            Text("A través de la página de contacto")
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Alerta Devolucion"
            )
        }
    )
}

@Composable
fun AlertaSeguridad(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },
        title = { Text("Seguridad") },
        text = {
            Text("a")
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Alerta Compra"
            )
        }
    )
}