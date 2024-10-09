package com.ars.zazil.View.Login

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ars.zazil.R
import com.ars.zazil.View.Pantallas
import com.ars.zazil.Viewmodel.LoginVM
import com.ars.zazil.ui.theme.Black
import com.ars.zazil.ui.theme.focusedTextFieldText
import com.ars.zazil.ui.theme.fondo
import com.ars.zazil.ui.theme.textFieldContainer
import com.ars.zazil.ui.theme.unfocusedTextFieldText

@Composable
fun IniciarSesion(loginVM: LoginVM, navController: NavHostController) {
    Surface (color = fondo){
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            Spacer(modifier = Modifier.height(15.dp))
            BottomSection(loginVM,navController)
        }
    }
}

@Composable
private fun BottomSection(loginVM: LoginVM, navController: NavHostController) {
    var mail by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val context = LocalContext.current
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isConnected by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isConnected = checkInternetConnection(context)
    }
    val loginEstado = loginVM.estadoLogin.collectAsState()
    var logueado by remember { mutableStateOf(false) }

    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    if(!logueado && loginEstado.value){
        navController.navigate(Pantallas.RUTA_PRINCIPAL){
            logueado = true
            navOptions {
                popUpTo(Pantallas.RUTA_INICIO){
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        if(showError){
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = mail,
            onValueChange = { mail = it},
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.labelMedium,
                    color = uiColor
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            )
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .background(Color.Transparent),
            value = contrasena,
            onValueChange = { contrasena = it},
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(
                    text = "Contraseña",
                    style = MaterialTheme.typography.labelMedium,
                    color = uiColor
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
                unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            ),
            trailingIcon = {
                TextButton(onClick = {}) {
                    Text(
                        text = "Olvidaste tu contaseña?",
                        style = MaterialTheme.typography.labelMedium,
                        color = uiColor
                    )
                }
            }
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        Button(
            modifier = Modifier
                .padding(start = 110.dp)
                .height(40.dp),
            onClick = {
                if (!isConnected) {
                    showError = true
                    errorMessage = "No hay conexión a internet, por favor verifica tu conexión."
                } else if (mail.isEmpty() || contrasena.isEmpty()) {
                    showError = true
                    errorMessage = "Por favor ingresa tu email y contraseña."
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    showError = true
                    errorMessage = "Por favor ingresa un email válido."
                } else {
                    showError = false
                    val response = loginVM.login(mail, contrasena)
                    loginVM.setEstadoLogin(response)
                }
            },
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
            onClick = { navController.navigate(Pantallas.RUTA_CREARCUENTA) },
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

fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Zazil
        Image(
            modifier = Modifier
                .size(180.dp)
                .padding(top = 40.dp),
            painter = painterResource(id = R.drawable.logo_zazil),
            contentDescription = "Logo Zazil",
        )

        // Texto "Iniciar Sesión"
        Text(
            text = stringResource(id = R.string.Iniciar_Sesion),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
