package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.zazil.R

// Preview con medidas de un celular
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ContactUsPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Contáctanos",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Teléfono",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 20.sp
        )

        Text(
            text = "+52 56 28 08 3883",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Correo",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 20.sp
        )

        Text(
            text = "zazil@todasbrillamos.org",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebookicon),
                contentDescription = "Facebook",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.instagramicon),
                contentDescription = "Instagram",
                modifier = Modifier.size(75.dp)
            )
        }

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(110.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.youtubeicon),
                contentDescription = "YouTube",
                modifier = Modifier.size(80.dp)
            )
        }
    }
}