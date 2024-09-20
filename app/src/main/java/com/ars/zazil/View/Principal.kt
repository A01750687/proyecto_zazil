package com.ars.zazil.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.zazil.Model.Product
import com.ars.zazil.R
import com.ars.zazil.ui.theme.fondo

@ExperimentalMaterial3Api
@Preview(showBackground = true, widthDp = 400, heightDp = 800)

@Composable
fun Principal(modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Spacer(modifier = Modifier.height(16.dp))
        FilBus()
        Productos()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Zazil",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            )
        },
        modifier = Modifier.height(70.dp),
        actions = {
            IconButton(onClick = { /*TODO: Calendario*/ },
                modifier = Modifier.padding(bottom = 15.dp)) {
                Icon(painter = painterResource(id = R.drawable.calendario),
                    contentDescription = "Calendario")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO: Barra lateral*/ },
                modifier = Modifier.padding(bottom = 15.dp)) {
                Icon(painter = painterResource(id = R.drawable.tres),
                    contentDescription = "Lateral")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = fondo
        )
    )
}

@Composable
fun FilBus() {
    Column(
        modifier = Modifier
            .padding(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextField(
                value = "",
                onValueChange = {/*TODO: Búsqueda*/ },
                placeholder = { Text("Buscar...",
                    style = TextStyle(fontSize = 8.sp))
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(200.dp),
                shape = RoundedCornerShape(30.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.filtro),
                    contentDescription = "Filtro")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Filtros",
                fontSize = 18.sp)
        }
    }

}

@Composable
fun Productos(modifier: Modifier = Modifier) {
    var currentPage by remember { mutableStateOf(0)}
    val productos = listOf(
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 150, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image),
        Product("Toalla Femenina Reutilizable Regular", 180, R.drawable.image)
    )

    val productosMostrados = productos.chunked(10)
    val totalPag = productosMostrados.size

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(16.dp)
        ) {
            items(productosMostrados[currentPage].chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { product ->
                        ProductCard(product)
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (currentPage > 0) {
                    currentPage--
                }
            },
                enabled = currentPage > 0
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Anterior")
            }

            Text(
                text = "${currentPage + 1}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            IconButton(onClick = {
                if (currentPage < totalPag - 1) {
                    currentPage++
                }
            },
                enabled = currentPage < totalPag - 1
            ) {
                Icon(imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Siguiente")
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card (
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable { /*TODO: Info del producto*/ }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imagen),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.nombre,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${product.precio}",
                color = Color.Black,
                fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { /*TODO: Añadir al carrito*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir al carrito",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = fondo,
        modifier = Modifier.height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.telefono),
                    contentDescription = "Contactanos")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.carrito),
                    contentDescription = "Carrito")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.pregunta),
                    contentDescription = "Preguntas")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.pago),
                    contentDescription = "Métodos")
            }
        }
    }
}