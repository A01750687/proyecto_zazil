package com.ars.zazil.Viewmodel


import androidx.lifecycle.ViewModel
import com.ars.zazil.Model.ProductoCarrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * CarritoVM
 * Esta clase es un ViewModel que gestiona el estado de los productos en el carrito de compras de la aplicación.
 * Utiliza `MutableStateFlow` para almacenar y actualizar la lista de productos en el carrito, y proporciona
 * varios métodos para interactuar con el carrito (agregar, eliminar, aumentar o disminuir cantidades, y limpiar el carrito).
 */
class CarritoVM : ViewModel() {
    // MutableStateFlow para gestionar el estado de los productos en el Carrito
    private val _productosEnCarrito = MutableStateFlow<List<ProductoCarrito>>(emptyList())
    val productosEnCarrito: StateFlow<List<ProductoCarrito>> = _productosEnCarrito

    /**
     * agregarProducto
     * Agrega un producto al carrito. Si el producto ya está en el carrito, incrementa su cantidad.
     * Si no está, lo agrega a la lista. Retorna un valor booleano que indica si la operación fue exitosa.
     *
     * @param producto El producto que se desea agregar al carrito.
     * @return `true` si el producto se agregó o se actualizó exitosamente,
     *         `false` si no se pudo agregar debido a que se excede el stock disponible.
     */
    fun agregarProducto(producto: ProductoCarrito): Boolean {

        val productoExistente = _productosEnCarrito.value.find { it.producto.id == producto.producto.id }

        if (productoExistente != null) {
            // Verificar si se puede aumentar la cantidad sin exceder el stock
            val nuevaCantidad = productoExistente.cantidad + producto.cantidad
            if (nuevaCantidad <= producto.producto.stock) {
                val productosActualizados = _productosEnCarrito.value.map {
                    if (it == productoExistente) it.copy(cantidad = nuevaCantidad) else it
                }
                _productosEnCarrito.value = productosActualizados
                return true // Producto actualizado exitosamente
            } else {
                return false // No se puede agregar más productos debido al límite de stock
            }
        }

        // Si el producto no está en el carrito, agregarlo
        if (producto.cantidad <= producto.producto.stock) {
            _productosEnCarrito.value = _productosEnCarrito.value + producto
            return true // Producto agregado exitosamente
        }

        return false // No se puede agregar porque la cantidad inicial excede el stock
    }


    /**
     * obtenerProductos
     * Devuelve el `StateFlow` que contiene la lista de productos en el carrito.
     *
     * @return Estado público de solo lectura de la lista de productos en el carrito.
     */
    fun obtenerProductos(): StateFlow<List<ProductoCarrito>> {
        return productosEnCarrito
    }

    /**
     * ObtenerProducto
     * Devuelve un producto específico del carrito según su nombre.
     *
     * @param name El nombre del producto que se desea obtener.
     */
    fun obtenerProducto(name: String): ProductoCarrito? {
        return _productosEnCarrito.value.find { it.producto.nombre == name }
    }

    /**
     * eliminarProducto
     * Elimina un producto específico del carrito.
     *
     * @param producto El producto que se desea eliminar del carrito.
     */
    fun eliminarProducto(producto: ProductoCarrito) {
        _productosEnCarrito.value = _productosEnCarrito.value - producto
    }

    /**
     * limpiarCarrito
     * Limpia completamente el carrito, eliminando todos los productos.
     */
    fun limpiarCarrito() {
        _productosEnCarrito.value = emptyList()
    }

    /**
     * aumentarCantidad
     * Aumenta la cantidad de un producto específico en el carrito.
     *
     * @param producto El producto cuya cantidad se desea aumentar.
     */
    fun aumentarCantidad(producto: ProductoCarrito) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto && it.cantidad < producto.producto.stock) it.copy(cantidad = it.cantidad + 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }

    /**
     * disminuirCantidad
     * Disminuye la cantidad de un producto específico en el carrito, siempre que su cantidad sea mayor que 1.
     *
     * @param producto El producto cuya cantidad se desea disminuir.
     */
    fun disminuirCantidad(producto: ProductoCarrito) {
        val productosActualizados = _productosEnCarrito.value.map {
            if (it == producto && it.cantidad > 1) it.copy(cantidad = it.cantidad - 1) else it
        }
        _productosEnCarrito.value = productosActualizados
    }
}
