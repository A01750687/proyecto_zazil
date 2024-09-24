package com.ars.zazil.View.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ars.zazil.R
import com.ars.zazil.View.LoginTextField
import com.ars.zazil.ui.theme.Black
import com.ars.zazil.ui.theme.fondo

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun IniciarSesion() {
    Surface (color = fondo){
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            Spacer(modifier = Modifier.height(36.dp))
            BottomSection()
        }
    }

}

@Composable
private fun BottomSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        LoginTextField(
            label = "Celular",
            trailing = "",
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        LoginTextField(
            label = "Contraseña",
            trailing = "Olvidaste tu contaseña?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .background(Color.Transparent)


        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        Button(
            modifier = Modifier
                .padding(start = 110.dp)
                .height(40.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black

            ),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Text(
                text = "Iniciar Sesion",
                style = MaterialTheme.typography.labelMedium
            )


        }
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        Button(
            modifier = Modifier
                .padding(start = 110.dp)
                .height(40.dp),
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
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Juntar solo en imagen
            Image(
                modifier = Modifier.size(180.dp),
                painter = painterResource(id = R.drawable.logo_zazil),
                contentDescription = "Logozazil",
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.Iniciar_Sesion),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )
    }
}