package com.ars.zazil.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ars.zazil.Model.Qa
import com.ars.zazil.Viewmodel.PreguntasViewModel


@Composable
fun preguntasFrec(modifier: Modifier, viewModel: PreguntasViewModel = viewModel(), navController: NavHostController = rememberNavController()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Preguntas frecuentes",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Obtener el estado de la lista de preguntas desde el ViewModel
        val preguntas by viewModel.preguntas.collectAsState()

        // Mostrar las preguntas en una LazyColumn
        LazyColumn {
            items(preguntas) { pregunta ->
                // Mostrar cada pregunta y su respuesta
                Box(modifier = Modifier.clickable { navController.navigate("detalle/${pregunta.pregunta}/${pregunta.respuesta}") }) {
                    Text(
                        text = pregunta.pregunta,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun detallePregunta(pregunta: Qa, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = pregunta.pregunta,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp))

        Text(text = pregunta.respuesta,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp))
    }
}