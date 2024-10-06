package com.mags.pruebas.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ars.zazil.Model.ServicioRemoto
import com.ars.zazil.Viewmodel.LoginVM

@Composable
fun Pedidos_Pasados(loginVM: LoginVM, modifier: Modifier = Modifier) {

    loginVM.descargarPedidos()

    val pedidos = loginVM.pedidosState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {
        Text(
            text = "Pedidos Pasados",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom= 16.dp)
        )
        LazyColumn(){
            pedidos.value.forEach { pedido ->
                item {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFDBDAF6)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "DD/MM/AAAA", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                if(pedido.estado){
                                    Text(text = "Enviado", style = MaterialTheme.typography.bodyMedium,fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }else{
                                    Text(text = "Pendiente", style = MaterialTheme.typography.bodyMedium,fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }
                            }
                            pedido.items.forEach { item ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = rememberAsyncImagePainter(ServicioRemoto.URL + item.producto.imagen),
                                        contentDescription = null,
                                        modifier = Modifier.size(112.dp),
                                        contentScale = ContentScale.FillBounds
                                    )

                                    Spacer(modifier = Modifier.width(18.dp))

                                    Column {
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(text = item.producto.nombre, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(text = "Cantidad: ${item.cantidad}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Button(
                                            onClick = { /* TODO */ },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF7572CB),
                                                contentColor = Color.Black
                                            ),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .defaultMinSize(minWidth = 0.dp),
                                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
                                        ) {Text(text = "VOLVER A COMPRAR", fontSize = 12.sp, fontWeight = FontWeight.Bold)}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}