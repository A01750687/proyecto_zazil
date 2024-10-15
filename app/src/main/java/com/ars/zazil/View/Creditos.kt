package com.ars.zazil.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Creditos(modifier: Modifier) {

    val scrollSate = rememberScrollState()

    Column(modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth().verticalScroll(scrollSate)) {
        Text(text = "Créditos",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black)
        Text(
            text = "Proyecto desarrollado por estudiantes del Tecnológico de Monterrey " +
                    "campus CEM para la fundación Todas Brillamos.\n",
            textAlign = TextAlign.Center
        )
        Text(text = "Desarrolladores:\n" +
                "Aislinn Ruiz Sandoval:\n aislinn1284@gmail.com\n\n" +
                "David Sanchez Baez:\n david.sanchez@live.com.mx\n\n" +
                "Eduardo Serrano Corona:\n eduserrano.corona@gmail.com\n\n" +
                "Miguel Angel Galicia Sanchéz:\n miguelg987d@gmail.com\n\n" +
                "Víctor Alejandro Morales García:\n morales.victor0424@gmail.com",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}