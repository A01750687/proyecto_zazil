package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.zazil.R


@Composable
fun pagos(modifier: Modifier) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.padding(16.dp).verticalScroll(scrollState)) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
            text = "Métodos de pago",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.stripeimg),
                contentDescription = null,
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Text(text = "Pago por medio de stripe.",
                modifier = Modifier.weight(1f).padding(8.dp))
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row {
            ColorBoxWithText(boxColor = Color(0xFF5885C6), text = "Transferencia bancaria")
            Text(text = "Pago por aplicación bancaria hacia nuestro número de cuenta.",
                modifier = Modifier.weight(1f).padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row {
            ColorBoxWithText(boxColor = Color(0xFFFFE084), text = "Depósito")
            Text(text = "Pago en bancos o tienda como Oxxo.",
                modifier = Modifier.weight(1f).padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            thickness = 1.dp,    // Grosor de la línea
            color = Color.Gray  // El color de la línea
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Row {
            ColorBoxWithText(boxColor = Color(0xFFD22973), text = "Donar")
            Text(text = "El donar nos ayuda a crear más productos y así apoyamos con una toalla reutilizable a mujeres en situación de pobreza.",
                modifier = Modifier.weight(1f).padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
        }
    }
}

@Composable
fun ColorBoxWithText(boxColor: Color, text: String) {
    Box(
        modifier = Modifier
            .size(180.dp, 50.dp) // Tamaño de la caja
            .background(
                color = boxColor, // Color que se pasa como parámetro
                shape = RoundedCornerShape(8.dp) // Esquinas redondeadas (opcional)
            ),
        contentAlignment = Alignment.Center // Centra el texto dentro de la caja
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            color = Color.Black, // Color del texto
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}