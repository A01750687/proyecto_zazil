package com.ars.zazil.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.zazil.R
import com.ars.zazil.ui.theme.fondo

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun loginvista() {
    Surface(color = fondo) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Imagen()
            Botones()
        }
    }

}

@Composable
private fun Botones() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Botinicio()
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        Text(
            text = "o",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        BotCrear()
        Spacer(
            modifier = Modifier.height(25.dp)
        )
        BotGoogle()

    }
}

@Composable
private fun BotGoogle() {
    Button(
        modifier = Modifier
            .height(50.dp)
            .width(350.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_google),
            contentDescription = "Logo de Google",
            modifier = Modifier.size(36.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Continuar con Google",
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun BotCrear() {
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
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun Botinicio() {
    Button(
        modifier = Modifier
            .height(50.dp)
            .width(200.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,

            ),
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Text(
            text = "Iniciar Sesion",
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelMedium
        )
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

