package com.ars.zazil.View

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, widthDp = 340, heightDp = 600)
@Composable
fun EditarP(modifier: Modifier = Modifier) {
    val scrollSate = rememberScrollState()

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
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Dirección:",
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Edad:",
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Celular:",
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Género:",
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (){
            Text(text = "Contraseña:",
                modifier = Modifier.weight(1.4f).padding(top = 17.dp))
            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .height(50.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Guardar")
        }
    }
}