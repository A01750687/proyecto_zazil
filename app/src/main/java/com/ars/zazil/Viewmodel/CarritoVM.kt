package com.ars.zazil.Viewmodel


import androidx.lifecycle.ViewModel
import com.ars.zazil.Model.ProductoCarrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoVM : ViewModel() {
    // MutableStateFlow para gestionar el estado de los productos en el Carrito
    private val _productosEnCarrito = MutableStateFlow<List<ProductoCarrito>>(emptyList())
    val productosEnCarrito: StateFlow<List<ProductoCarrito>> = _productosEnCarrito

    // Método para agregar productos al Carrito
    fun agregarProducto(producto: ProductoCarrito) {

        val productoExistente = _productosEnCarrito.value.find { it.producto.id == producto.producto.id }

        if (productoExistente != null) {

            val productosActualizados = _productosEnCarrito.value.map {
                if (it == productoExistente) it.copy(cantidad = it.cantidad + 1) else it
            }
            _productosEnCarrito.value = productosActualizados
            return
        }
        // Si el producto no está en el Carrito, agregarlo
        _productosEnCarrito.value = _productosEnCarrito.value + producto
    }

    // Método para obtener los productos
    fun obtenerProductos(): StateFlow<List<ProductoCarrito>> {
        return productosEnCarrito
    }

    // Método para eliminar productos del Carrito
    fun eliminarProducto(producto: ProductoCarrito) {
        _productosEnCarrito.value = _productosEnCarrito.value - producto
    }

    // Método para limpiar el Carrito
    fun limpiarCarrito() {
        _productosEnCarrito.value = emptyList()
    }

    // Método para aumentar la cantidad de un producto
    fun aumentarCantidad(producto: ProductoCarrito) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto) it.copy(cantidad = it.cantidad + 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }

    // Método para disminuir la cantidad de un producto
    fun disminuirCantidad(producto: ProductoCarrito) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto && it.cantidad > 1) it.copy(cantidad = it.cantidad - 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }
}
