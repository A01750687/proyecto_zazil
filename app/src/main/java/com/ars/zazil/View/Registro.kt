package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ars.zazil.R
import com.ars.zazil.ui.theme.fondo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ars.zazil.Viewmodel.LoginVM
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun Registro(loginVM: LoginVM, navController: NavHostController) {
    Surface(color = fondo) {
        // Valores de los campos (gestión central del estado)
        var nombre by remember { mutableStateOf("") }
        var direccion by remember { mutableStateOf("") }
        var edad by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Imagen()
            CamposRegistro(
                nombre = nombre,
                onNombreChange = { nombre = it },
                direccion = direccion,
                onDireccionChange = { direccion = it },
                edad = edad,
                onEdadChange = { edad = it },
                email = email,
                onEmailChange = { email = it },
                contrasena = contrasena,
                onContrasenaChange = { contrasena = it })
            SeleccionGenero()
            Spacer(modifier = Modifier.height(15.dp))
            BotonCrearCuenta(loginVM, navController, nombre, direccion, edad, email, contrasena)
        }
    }
}


@Composable
private fun Imagen() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 10.dp),
        painter = painterResource(id = R.drawable.logo_zazil),
        contentDescription = null,
    )
}

@Composable
fun CamposRegistro(
    nombre: String, onNombreChange: (String) -> Unit,
    direccion: String,
    onDireccionChange: (String) -> Unit,
    edad: String,
    onEdadChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    contrasena: String,
    onContrasenaChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Darnombre(nombre, onNombreChange)
        Spacer(modifier = Modifier.height(10.dp))
        DarDireccion(direccion, onDireccionChange)
        Spacer(modifier = Modifier.height(10.dp))
        DarEdad(edad, onEdadChange)
        Spacer(modifier = Modifier.height(10.dp))
        DarCelular(email, onEmailChange)
        Spacer(modifier = Modifier.height(10.dp))
        DarContrasena(contrasena, onContrasenaChange)
    }
}

@Composable
fun SeleccionGenero() {
    var expandido by remember { mutableStateOf(false) }
    val opciones = listOf("Femenino", "Masculino", "Otro")
    var selectedOption by remember { mutableStateOf(opciones[0]) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedButton(
            onClick = { expandido = !expandido }
        ) {
            Text(text = selectedOption)
            Icon(
                imageVector = if (expandido) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedOption = label
                        expandido = false
                    }
                )
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return emailPattern.matches(email)
}

@Composable
fun BotonCrearCuenta(
    loginVM: LoginVM,
    navController: NavHostController,
    nombre: String,
    direccion: String,
    edad: String,
    email: String,
    contrasena: String
) {
    val estado = loginVM.estadoRegistro.collectAsState()
    var cambioPantalla by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar error en caso de que algún campo esté vacío o el correo sea inválido
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(12.dp)
            )
        }

        if (!estado.value.first) {
            Text(
                text = estado.value.second,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Red
            )
        }

        if (estado.value.first && !cambioPantalla) {
            navController.navigate(Pantallas.RUTA_INICIO_SESION) {
                cambioPantalla = true
            }
        }

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = {
                // Validaciones de los campos
                if (nombre.isEmpty() || direccion.isEmpty() || edad.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                    errorMessage = "Por favor, llene todos los campos."
                } else if (!isValidEmail(email)) {
                    errorMessage = "Correo electrónico no válido."
                } else {
                    errorMessage = ""
                    loginVM.registro(nombre, direccion, edad.toInt(), email, contrasena, "Femenino")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Text(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun DarContrasena(contrasena: String, onContrasenaChange: (String) -> Unit) {
    TextField(
        value = contrasena,
        onValueChange = onContrasenaChange,  // Actualizamos el valor
        label = { Text("Contraseña") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        visualTransformation = PasswordVisualTransformation()  // Ocultamos la contraseña
    )
}

@Composable
fun DarCelular(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,  // Actualizamos el valor
        label = { Text("email") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun DarEdad(edad: String, onEdadChange: (String) -> Unit) {
    TextField(
        value = edad,
        onValueChange = onEdadChange,  // Actualizamos el valor
        label = { Text("Edad") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun DarDireccion(direccion: String, onDireccionChange: (String) -> Unit) {
    TextField(
        value = direccion,
        onValueChange = onDireccionChange,  // Actualizamos el valor
        label = { Text("Dirección") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun Darnombre(nombre: String, onNombreChange: (String) -> Unit) {
    TextField(
        value = nombre,
        onValueChange = onNombreChange,
        label = { Text("Nombre") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

/*@Composable
fun Fecha() {
    val context = LocalContext.current
    var date by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

    OutlinedButton(
        onClick = {
            datePickerDialog.show()
        }
    ) {
        Text(text = if (date.isBlank()) "Seleccionar Fecha" else date)
        Icon(imageVector = Icons.Filled.CalendarToday, contentDescription = null)
    }
}*/