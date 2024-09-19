package com.ars.zazil.Viewmodel


import androidx.lifecycle.ViewModel
import com.ars.zazil.Model.producto
import com.ars.zazil.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoVM : ViewModel() {
    // MutableStateFlow para gestionar el estado de los productos en el carrito
    private val _productosEnCarrito = MutableStateFlow<List<producto>>(emptyList())
    val productosEnCarrito: StateFlow<List<producto>> = _productosEnCarrito

    // Método para agregar productos al carrito
    fun agregarProducto(producto: producto) {
        _productosEnCarrito.value = _productosEnCarrito.value + producto
    }

    // Método para obtener los productos
    fun obtenerProductos(): StateFlow<List<producto>> {
        return productosEnCarrito
    }

    // Método para eliminar productos del carrito
    fun eliminarProducto(producto: producto) {
        _productosEnCarrito.value = _productosEnCarrito.value - producto
    }

    // Método para limpiar el carrito
    fun limpiarCarrito() {
        _productosEnCarrito.value = emptyList()
    }

    // Método para aumentar la cantidad de un producto
    fun aumentarCantidad(producto: producto) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto) it.copy(cantidad = it.cantidad + 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }

    // Método para disminuir la cantidad de un producto
    fun disminuirCantidad(producto: producto) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto && it.cantidad > 1) it.copy(cantidad = it.cantidad - 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }

    // Simular productos al iniciar
    init {
        agregarProducto(producto("Producto 1", 10.99, R.drawable.compra, 2))
        agregarProducto(producto("Producto 2", 25.50, R.drawable.somos, 1))
        agregarProducto(producto("Producto 3", 7.99, R.drawable.periodo, 3))
        agregarProducto(producto("Producto 4", 10.99, R.drawable.compra, 2))
        agregarProducto(producto("Producto 5", 25.50, R.drawable.somos, 1))
        agregarProducto(producto("Producto 6", 7.99, R.drawable.periodo, 3))
        agregarProducto(producto("Producto 7", 10.99, R.drawable.compra, 2))
        agregarProducto(producto("Producto 8", 25.50, R.drawable.somos, 1))
        agregarProducto(producto("Producto 9", 7.99, R.drawable.periodo, 3))
    }
}
