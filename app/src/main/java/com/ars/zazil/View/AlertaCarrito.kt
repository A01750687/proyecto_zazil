package com.ars.zazil.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertaCarrito(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit, ){
    AlertDialog(
        icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null)},
        onDismissRequest = {
            onDismissRequest()
        },
        title = {Text("Carrito")},
        text = {Text("Se ha agregado un producto al carrito")},
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirmar")
            }
        },
    )
}