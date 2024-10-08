package com.ars.zazil.View

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val scrollState = rememberScrollState()
    val context = LocalContext.current // Aquí obtenemos el contexto correctamente

    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
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
            onClick = {
                val url = "https://www.facebook.com/FundacionTodasBrillamos?locale=es_LA"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try{
                    context.startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, "No se encontró la aplicación", Toast.LENGTH_SHORT).show()
                }

            },
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
            onClick = {
                val url = "https://www.instagram.com/fundaciontodasbrillamos/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try{
                    context.startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, "No se encontró la aplicación", Toast.LENGTH_SHORT).show()
                } },
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
            onClick = {
                val url = "https://www.youtube.com/@FundacionTodasBrillamos"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try{
                    context.startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, "No se encontró la aplicación", Toast.LENGTH_SHORT).show()
                }
            },
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
