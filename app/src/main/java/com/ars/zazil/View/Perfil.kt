package com.ars.zazil.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ars.zazil.Viewmodel.LoginVM

//@Preview(showBackground = true, widthDp = 340, heightDp = 600)
@Composable
fun Perfil(loginVM: LoginVM, navController: NavController, modifier: Modifier = Modifier) {
    val scrollSate = rememberScrollState()
    loginVM.descargarInfoUsuario()

    val loginEstado = loginVM.usuarioState.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollSate),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Mi Perfil",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        TextButton(
            onClick = {navController.navigate(Pantallas.RUTA_EDITARPERFIL)},
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Editar",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Editar",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }

        // Datos del usuario
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                SectionCard(
                    title = "Nombre",
                    value = loginEstado.value.nombre,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
                SectionCard(
                    title = "Edad",
                    value = "${loginEstado.value.edad}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
                SectionCard(
                    title = "Celular",
                    value = "+52 ${loginEstado.value.numero}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            SectionCard(
                title = "Dirección",
                value = loginEstado.value.direccion,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                SectionCard(
                    title = "Género",
                    value = "Femenino",
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp))
                SectionCard(
                    title = "Contraseña",
                    value = "********",
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp))
            }
        }
    }
}

@Composable
fun SectionCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .heightIn(min = 72.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}