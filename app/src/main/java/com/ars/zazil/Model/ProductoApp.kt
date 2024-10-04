package com.ars.zazil.Model

data class ProductoApp(
    val id: String = "",
    val nombre: String = "",
    val stock: Int = 0,
    val precio: Double = 0.0,
    val descuento: Double = 0.0,
    val descripcion: String = "",
    val categoria: String = "",
    val imagen: String = ""
)
