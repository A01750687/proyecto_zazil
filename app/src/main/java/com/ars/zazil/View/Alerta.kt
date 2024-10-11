package com.ars.zazil.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ListAlt
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
                    "- Añade al Carrito.\n" +
                    "\n" +
                    "- Puedes seguir añadiendo más productos al Carrito o proceder a pagar lo que esté en el Carrito.\n" +
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
        title = { Text("Politica de devolución") },
        text = {
            Text("Por ser una prenda íntima y de uso personal, los cambios en este producto no son procedentes. Atendiendo a la Ley Federal de Protección al consumidor en México (PROFECO) únicamente realizaremos cambios por defecto de fábrica. * Aplica únicamente dentro de los primeros 5 días posteriores a la entrega *")
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
        title = { Text("Privacidad") },
        text = {
            // Añadimos scroll al contenido del AlertDialog
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    """
                    AVISO DE PRIVACIDAD
                    
                    En Fundación Todas Brillamos AC, valoramos la privacidad de nuestros clientes y nos comprometemos a proteger la información personal que nos proporcionan. Esta política de privacidad explica cómo recopilamos, utilizamos y protegemos sus datos personales.
                    
                    INFORMACIÓN RECOLECTADA
                    
                    Datos personales: nombre, dirección, correo electrónico, número de teléfono
                    Información de pago: tarjeta de crédito, débito o PayPal
                    
                    USO DE LA INFORMACIÓN
                    
                    Procesar y enviar pedidos
                    Enviar correos electrónicos con promociones y ofertas especiales
                    Mejorar nuestra tienda online y experiencia de usuario
                    
                    PROTECCIÓN DE LA INFORMACIÓN
                    
                    Utilizamos medidas de seguridad para proteger sus datos personales
                    No compartimos información personal con terceros, excepto para procesar pedidos y envíos
                    
                    DERECHOS DE LOS CLIENTES
                    
                    Acceder, rectificar o cancelar su información personal en cualquier momento
                    Oponerse al uso de su información para fines de marketing
                    
                    CAMBIOS EN LA POLÍTICA DE PRIVACIDAD
                    
                    Podemos actualizar esta política de privacidad en cualquier momento
                    Se notificará a los clientes de cualquier cambio significativo
                    
                    FECHA DE ÚLTIMA ACTUALIZACIÓN: 2 de Septiembre 2024
                    
                    Si tienes alguna pregunta o inquietud, por favor no dudes en contactarnos.
                    """.trimIndent()
                )
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Default.ListAlt,
                contentDescription = "Alerta Seguridad"
            )
        },
        modifier = Modifier.fillMaxHeight(0.8f) // Ajustar el tamaño del AlertDialog
    )
}
