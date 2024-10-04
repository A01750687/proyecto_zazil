package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.Model.ProductoCarrito

class productoVM(private val carritoVM: CarritoVM) : ViewModel() {

    fun setProducto(producto: ProductoApp, cantidad: Int) {
        val nuevoproducto = ProductoCarrito(producto, cantidad)
        carritoVM.agregarProducto(nuevoproducto)
    }


}