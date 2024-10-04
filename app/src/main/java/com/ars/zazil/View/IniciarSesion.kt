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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ars.zazil.Model.LoginState
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
            Spacer(modifier = Modifier.height(36.dp))
            BottomSection(loginVM,navController)
        }
    }

}

@Composable
private fun BottomSection(loginVM: LoginVM, navController: NavHostController) {
    var mail by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val loginState by loginVM.loginState.collectAsState()
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
            ),
            trailingIcon = {
                TextButton(onClick = {}) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.labelMedium,
                        color = uiColor
                    )
                }
            }
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
                val response = loginVM.login(mail,contrasena)
                loginVM.setEstadoLogin(response)
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