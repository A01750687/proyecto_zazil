package com.ars.zazil.View


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ars.zazil.R
import com.ars.zazil.Viewmodel.GoogleVM
import com.ars.zazil.ui.theme.fondo
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun Inicio(googleVM: GoogleVM, navController: NavHostController) {
    Surface(color = fondo) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Imagen()
            Botones(googleVM,navController)
        }
    }

}

@Composable
private fun Botones(googleVM: GoogleVM, navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Botinicio(navController)
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
        BotCrear(navController)
        Spacer(
            modifier = Modifier.height(25.dp)
        )
        BotGoogle(googleVM,navController)

    }
}

@Composable
fun BotGoogle(googleVM: GoogleVM,navController: NavHostController) {
    val token = "783043939229-10fm2pgnufnis20oqhihb437s41rlkar.apps.googleusercontent.com"
    val contexto = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val cuenta = task.getResult(ApiException::class.java)
            val credencial = GoogleAuthProvider.getCredential(cuenta.idToken, null)
            googleVM.hacerLoginGoogle(credencial) {
                googleVM.setEstadoLogin(true)
            }
        } catch(e: Exception) {
            println("EXEPCION haciendo Login: ${e.localizedMessage}")
        }
    }


    Button(
        modifier = Modifier
            .height(50.dp)
            .width(350.dp),
        onClick = {
            val opciones = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
            val clienteGoogle = GoogleSignIn.getClient(contexto, opciones)
            launcher.launch(clienteGoogle.signInIntent)
            navController.navigate(Pantallas.RUTA_CREARCUENTA)
        },
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
private fun BotCrear(navController: NavHostController) {
    Button(
        modifier = Modifier
            .height(50.dp)
            .width(200.dp),
        onClick = {
            navController.navigate(Pantallas.RUTA_CREARCUENTA)
        },
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
private fun Botinicio(navController: NavHostController) {
    Button(
        modifier = Modifier
            .height(50.dp)
            .width(200.dp),
        onClick = {
            navController.navigate(Pantallas.RUTA_INICIO_SESION)
        },
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

