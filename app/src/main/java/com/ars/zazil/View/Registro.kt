package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
import androidx.navigation.NavHostController

@Composable
fun Registro(navController: NavHostController) {
    Surface(color = fondo) {
        Column(
            modifier = Modifier.fillMaxSize().padding(26.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Imagen()
            CamposDeRegistro()
            Spacer(modifier = Modifier.height(15.dp))
            SelectorDeGenero(modifier = Modifier)
            Fecha()
            Spacer(modifier = Modifier.height(15.dp))
            CrearCuenta()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDeGenero(modifier: Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf<Gender?>(null) }
    val icon = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        }
    ) {
        TextField(
            value = selectedGender,
            onValueChange = { },
            readOnly = true,
            modifier = modifier,
            label = { Text("Género") },
            trailingIcon = {
                Icon(icon, contentDescription = if (isExpanded) "Collapse menu" else "Expand menu")
            }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            val options = listOf("Mujer", "Hombre", "Otro")
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedGender = selectionOption
                        isExpanded = false
                    }
                )
            }
        }
    }
}

private const val SELECT_GENDER = "Seleccione Género"

enum class Gender(val displayText: String) {
    FEMALE("Femenino"),
    MALE("Masculino"),
    OTHER("Otro")
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
            .height(300.dp)
            .padding(bottom = 100.dp),
        painter = painterResource(id = R.drawable.logo_zazil),
        contentDescription = null,
    )
}

@Composable
private fun CamposDeRegistro() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = "",
            onValueChange = { /* Manejar cambio de Nombre */ },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = "",
            onValueChange = { /* Manejar cambio de Dirección */ },
            label = { Text("Dirección") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = "",
            onValueChange = { /* Manejar cambio de Edad */ },
            label = { Text("Edad") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = "",
            onValueChange = { /* Manejar cambio de Celular */ },
            label = { Text("Celular") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = "",
            onValueChange = { /* Manejar cambio de Contraseña */ },
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}
