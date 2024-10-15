package com.ars.zazil.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ars.zazil.Viewmodel.LoginVM

@Composable
fun EditarP(loginVM: LoginVM, navController: NavHostController, modifier: Modifier = Modifier) {
    val scrollSate = rememberScrollState()

    val estadoUsuario = loginVM.usuarioState.collectAsState()

    // Valores de los campos (gestión central del estado)
    var nombre by remember { mutableStateOf(estadoUsuario.value.nombre) }
    var direccion by remember { mutableStateOf(estadoUsuario.value.direccion) }
    var edad by remember { mutableStateOf(estadoUsuario.value.edad.toString()) }
    var email by remember { mutableStateOf(estadoUsuario.value.email) }
    var numero by remember { mutableStateOf(estadoUsuario.value.numero) }
    var contrasena by remember { mutableStateOf("") }



    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(scrollSate),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        Text(
            text = "Mi Perfil",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row () {
            Text(text = "Nombre:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = nombre, onValueChange = {
                nombre = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Dirección:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = direccion, onValueChange = {
                direccion = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Edad:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = edad, onValueChange = {
                edad = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Email:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = email, onValueChange = {
                email = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Celular:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = numero, onValueChange = {
                numero = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Contraseña:",
                modifier = Modifier
                    .weight(1.4f)
                    .padding(top = 17.dp))
            OutlinedTextField(value = contrasena, onValueChange = {
                contrasena = it
            },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        TextButton(onClick = {
            loginVM.editarUsuario(nombre,direccion,edad.toInt(),email,numero,contrasena)
            navController.navigate(Pantallas.RUTA_PERFIL)
        }) {
            Text(text = "Guardar")
        }
    }
}