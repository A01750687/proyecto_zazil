package com.ars.zazil.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Creditos(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Créditos",
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black)
        Text(text = "Desarrollado por:\n" +
                "Aislinn Ruiz Sandoval\n" +
                "David Sanchez Baez\n" +
                "Eduardo Serrano Corona\n" +
                "Miguel Angel Galicia Sanchéz\n" +
                "Víctor Alejandro Morales García",)
    }
}