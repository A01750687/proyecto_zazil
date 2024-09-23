package com.ars.zazil.Model

data class ProductoApp(
    val nombre: String = "",
    val stock: Int = 0,
    val precio: Float = 0f,
    val descuento: Float = 0f,
    val descripcion: String = "",
    val categoria: String = "",
    val imagen: String = ""
)
