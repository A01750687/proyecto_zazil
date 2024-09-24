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
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Registro(navController: NavHostController) {
    Surface(color = fondo) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Imagen()
            CamposDeRegistro()
            SelectorDeGenero(modifier = Modifier)
            Fecha(Modifier)
            Spacer(modifier = Modifier.height(15.dp))
            CrearCuenta()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDeGenero(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Seleccione Género") }
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = { },
            readOnly = true,
            modifier = modifier,
            label = { Text("Género") },
            trailingIcon = {
                Icon(icon, contentDescription = if (expanded) "Collapse menu" else "Expand menu")
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            val options = listOf("Mujer", "Hombre", "Otro")
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOption = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun Fecha(modifier: Modifier) {
    var date by remember { mutableStateOf("") }
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            date = "$dayOfMonth/${month + 1}/$year"
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    TextField(
        value = date,
        onValueChange = { },
        readOnly = true,
        modifier = modifier,
        label = { Text("Fecha de Nacimiento") },
        trailingIcon = {
            Icon(Icons.Filled.CalendarToday, contentDescription = null)
        }
    )
}

@Composable
private fun CrearCuenta() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = { /*TODO*/ },
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
private fun Imagen() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        painter = painterResource(id = R.drawable.logo_zazil),
        contentDescription = null,
    )
}
@Composable
private fun CamposDeRegistro() {
    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo Nombre
        TextField(
            value = nombre,
            onValueChange = { nombre = it },  // Actualizamos el valor con lo que escribe el usuario
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Dirección
        TextField(
            value = direccion,
            onValueChange = { direccion = it },  // Actualizamos el valor
            label = { Text("Dirección") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Edad
        TextField(
            value = edad,
            onValueChange = { edad = it },  // Actualizamos el valor
            label = { Text("Edad") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Celular
        TextField(
            value = celular,
            onValueChange = { celular = it },  // Actualizamos el valor
            label = { Text("Celular") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Contraseña
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },  // Actualizamos el valor
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            visualTransformation = PasswordVisualTransformation()  // Ocultamos la contraseña
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}
