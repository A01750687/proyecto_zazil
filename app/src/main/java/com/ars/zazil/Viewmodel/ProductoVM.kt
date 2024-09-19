package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import com.ars.zazil.Model.producto

class productoVM(private val carritoVM: CarritoVM) : ViewModel() {

    fun setProducto(nombre: String, precio: Double, imagen: Int, cantidad: Int) {
        val producto = producto(nombre, precio, imagen, cantidad)
        carritoVM.agregarProducto(producto)
    }


}