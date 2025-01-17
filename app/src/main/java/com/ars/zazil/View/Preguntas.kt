package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ars.zazil.R

@Composable
fun preguntas(modifier: Modifier, navController: NavHostController) {
    val scrollState = rememberScrollState()

    val showDialogSomos = remember { mutableStateOf(false) }
    val showDialogContenido = remember { mutableStateOf(false) }
    val showDialogCompra = remember { mutableStateOf(false) }
    val showDialogDevolucion = remember { mutableStateOf(false) }
    val showDialogSeguridad = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp).verticalScroll(scrollState)
    ){
        Text(text = "Preguntas frecuentes",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(
                onClick = { showDialogCompra.value = true },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f) // Controla el espacio que ocupa el botón
                    .size(120.dp) // Asegura que el IconButton sea grande
            ) {
                Image(
                    painter = painterResource(id = R.drawable.compra), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }

            IconButton(
                onClick = { showDialogDevolucion.value = true },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .size(120.dp) // Tamaño del IconButton
            ) {
                Image(
                    painter = painterResource(id = R.drawable.devolucion), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "¿Cómo comprar?",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Text(text = "Política de devolución",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(
                onClick = { showDialogContenido.value = true },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f) // Controla el espacio que ocupa el botón
                    .size(120.dp) // Asegura que el IconButton sea grande
            ) {
                Image(
                    painter = painterResource(id = R.drawable.contenido), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }

            IconButton(
                onClick = { navController.navigate("ListaPreguntas") },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .size(120.dp) // Tamaño del IconButton
            ) {
                Image(
                    painter = painterResource(id = R.drawable.periodo), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Contenido del producto",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Text(text = "Menstruación",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(
                onClick = { showDialogSomos.value = true },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f) // Controla el espacio que ocupa el botón
                    .size(120.dp) // Asegura que el IconButton sea grande
            ) {
                Image(
                    painter = painterResource(id = R.drawable.somos), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }

            IconButton(
                onClick = { showDialogSeguridad.value = true },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .size(120.dp) // Tamaño del IconButton
            ) {
                Image(
                    painter = painterResource(id = R.drawable.seguridad), // Reemplaza con tu imagen
                    contentDescription = "Botón de imagen",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "¿Quiénes somos?",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Text(text = "Aviso de Privacidad",
                modifier = Modifier.weight(1f).padding(8.dp).fillMaxWidth().align(Alignment.CenterVertically),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }

    }

    if (showDialogSomos.value) {
        AlertaSomos(onDismiss = { showDialogSomos.value = false })
    }

    if (showDialogContenido.value) {
        AlertaContenido(onDismiss = { showDialogContenido.value = false })
    }

    if (showDialogCompra.value) {
        AlertaCompra(onDismiss = { showDialogCompra.value = false })
    }

    if (showDialogDevolucion.value) {
        AlertaDevolucion(onDismiss = { showDialogDevolucion.value = false })
    }

    if (showDialogSeguridad.value) {
        AlertaSeguridad(onDismiss = { showDialogSeguridad.value = false })
    }
}