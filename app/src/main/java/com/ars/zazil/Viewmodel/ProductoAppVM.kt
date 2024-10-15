package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.Model.ServicioRemoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * ProductoAppVM
 * Esta clase es un ViewModel para gestionar el estado de los productos en la aplicación Zazil.
 * Utiliza Kotlin Flows para emitir cambios de estado y corutinas para realizar operaciones asincrónicas.
 */
class ProductoAppVM: ViewModel() {

    private val servicioRemoto = ServicioRemoto()

    private val _estado = MutableStateFlow(ProductoApp())
    val estado: StateFlow<ProductoApp> = _estado

    private val _estadoLista = MutableStateFlow(listOf<ProductoApp>())
    val estadoLista: StateFlow<List<ProductoApp>> = _estadoLista

    private val _estadoFiltrado = MutableStateFlow(listOf<ProductoApp>())
    val estadoFiltrado: StateFlow<List<ProductoApp>> = _estadoFiltrado

    private val _sinProductosDialog = MutableStateFlow(false)
    val sinProductosDialog: StateFlow<Boolean> = _sinProductosDialog

    /**
     * descargarProducto
     * Descarga la información de un producto específico desde un servicio remoto.
     * @param id Identificador del producto a descargar.
     */
    fun descargarProducto(id:String){
        viewModelScope.launch {
            val producto = servicioRemoto.descargarProducto(id)
            _estado.value = producto
        }
    }

    /**
     * descargarListaProducto
     * Descarga la lista completa de productos desde el servicio remoto.
     * Almacena la lista en _estadoLista y _estadoFiltrado para su uso en la aplicación.
     */
    fun descargarListaProducto(){
        viewModelScope.launch {
            val listaProducto = servicioRemoto.descargarlistaProducto()
            _estadoLista.value = listaProducto
            _estadoFiltrado.value = listaProducto
        }
    }

    /**
     * searchProducto
     * Filtra la lista de productos basada en una consulta de búsqueda (query).
     * Si la consulta está vacía, se muestran todos los productos; de lo contrario, filtra por coincidencias
     * en el nombre de los productos.
     * @param query Consulta de búsqueda que se usará para filtrar los productos.
     */
    fun searchProducto(query:String) {
        val listaFiltrada = if (query.isEmpty()) {
            _estadoLista.value
        } else {
            _estadoLista.value.filter { it.nombre.contains(query, ignoreCase = true) }
        }
        _estadoFiltrado.value = listaFiltrada
    }

    // Estado interno para almacenar múltiples términos de búsqueda como una lista de cadenas.
    private val _multipleQuery = MutableStateFlow<List<String>>(emptyList())
    // Exposición de _multipleQuery como un StateFlow para ser observado externamente.
    val multipleQuery: StateFlow<List<String>> = _multipleQuery

    private val _queryCategory = MutableStateFlow<List<String>>(emptyList())
    val queryCategory: StateFlow<List<String>> = _queryCategory

    // Función para agregar un nuevo término a la lista de búsqueda.
    fun agregarElemento(nuevoElemento: String) {
        // Actualiza _multipleQuery añadiendo el nuevo término al final de la lista.
        _multipleQuery.value = _multipleQuery.value + nuevoElemento
        // Imprime el contenido actualizado de la lista en consola.
        println("(AGREGAR) LISTA DE TERMINOS: ${_multipleQuery.value}")
    }

    fun agregarCategoria(nuevaCategoria: String) {
        // Actualiza _multipleQuery añadiendo el nuevo término al final de la lista.
        _queryCategory.value = _queryCategory.value + nuevaCategoria
        // Imprime el contenido actualizado de la lista en consola.
        println("(AGREGADO) LISTA DE CATEGORIAS: ${_queryCategory.value}")
    }

    fun eliminarCategoria(nuevaCategoria: String) {
        // Actualiza _multipleQuery añadiendo el nuevo término al final de la lista.
        _queryCategory.value = _queryCategory.value - nuevaCategoria
        // Imprime el contenido actualizado de la lista en consola.
        println("(ELIMINADO) LISTA DE CATEGORIAS: ${_queryCategory.value}")
    }

    // Función para eliminar un término específico de la lista de búsqueda.
    fun eliminarElemento(elemento: String) {
        // Actualiza _multipleQuery eliminando el término especificado de la lista.
        _multipleQuery.value = _multipleQuery.value - elemento
        // Imprime el contenido actualizado de la lista en consola.
        println("(ELIMINADO) LISTA DE TERMINOS: ${_multipleQuery.value}")
    }

    // Función para limpiar la lista de búsqueda y reiniciar todos los filtros.
    fun limpiarLista() {
        // Establece _multipleQuery a una lista vacía, eliminando todos los términos de búsqueda.
        _multipleQuery.value = emptyList()
        // Restablece el estado de las casillas de verificación a un mapa vacío.
        _checkboxStates.value = emptyMap()
        _queryCategory.value = emptyList()
        // Restablece los valores de precio mínimo y máximo a nulos.
        _minPrice.value = null
        _maxPrice.value = null
        // Imprime la lista vacía en consola, indicando que se ha limpiado.
        println(_multipleQuery.value)
    }


    // Función que aplica filtros a una lista de productos basada en términos de búsqueda y rangos de precios.
    fun aplicarFiltros() {
        // Imprime en consola los términos de búsqueda activos.
        println("Términos de búsqueda: ${_multipleQuery.value}")

        // Filtra la lista de productos según los términos de búsqueda.
        // Si no hay términos, devuelve la lista completa; si hay, filtra los productos cuyos nombres contienen todos los términos.
        val listaFiltrada = if (_multipleQuery.value.isEmpty()) {
            _estadoLista.value // Retorna la lista original si no hay términos de búsqueda.
        } else {
            _estadoLista.value.filter { producto ->
                // Filtra los productos para que contengan todos los términos de búsqueda ignorando mayúsculas/minúsculas.
                _multipleQuery.value.all { query ->
                    producto.nombre.contains(query, ignoreCase = true)
                }
            }
        }

        val listaFiltradaPorCategoria = if (_queryCategory.value.isEmpty()) {
            _estadoLista.value // Retorna la lista original si no hay términos de búsqueda.
        }else {
            _estadoLista.value.filter { producto ->
                println("Producto actual: ${producto.categoria}")
                // Filtra los productos para que contengan todos los términos de búsqueda ignorando mayúsculas/minúsculas.
                _queryCategory.value.all { query ->
                    producto.categoria.contains(query, ignoreCase = true)
                }
            }
        }


        // Aplica un segundo filtro basado en el rango de precios, verificando el precio mínimo y el máximo.
        val listaFiltradaPorPrecio = listaFiltradaPorCategoria.filter { producto ->
            val min = _minPrice.value // Obtiene el precio mínimo (si está definido).
            val max = _maxPrice.value // Obtiene el precio máximo (si está definido).
            val precio = producto.precio
            // Verifica que el precio del producto esté dentro del rango de precios especificado.
            (min == null || precio >= min) && (max == null || precio <= max)
        }

        if (listaFiltradaPorPrecio.isEmpty()) {
            println("No se encontraron productos que coincidan con los filtros.")
            println("TODOS LOS PRODUCTOS: ${_estadoFiltrado.value.map { it.nombre }}")
            _sinProductosDialog.value = true
            println("ESTADO DEL DIALOGO: ${_sinProductosDialog.value}")
        } else {
            println("Productos filtrados: ${listaFiltradaPorPrecio.map { it.nombre }}")
            _estadoFiltrado.value = listaFiltradaPorPrecio
        }
    }

    private val _checkboxStates = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val checkboxStates: StateFlow<Map<String, Boolean>> = _checkboxStates

    // Función para actualizar el estado de una casilla de verificación
    fun updateCheckboxState(label: String, isChecked: Boolean) {
        // Actualiza el estado de las casillas de verificación en el MutableStateFlow.
        _checkboxStates.value = _checkboxStates.value.toMutableMap().apply {
            this[label] = isChecked // Asigna el valor (true o false) a la etiqueta correspondiente.
        }

        // Agrega o elimina el elemento en función de si la casilla está marcada o no.
        if (isChecked) {
            if(label == "Flujo Abundante" || label == "Flujo Moderado" || label =="Flujo Ligero" || label =="Kit"){
                agregarCategoria(label)
            }else{
                agregarElemento(label)
            }
        } else {
            if(label == "Flujo Abundante" || label == "Flujo Moderado" || label =="Flujo Ligero" || label =="Kit"){
                eliminarCategoria(label)
            }else{
                eliminarElemento(label)
            }
        }
    }

    // Declaración de una variable de estado para el precio mínimo (nullable).
    private val _minPrice = MutableStateFlow<Double?>(null)
    // Exposición de la variable de estado _minPrice como un StateFlow para ser observada externamente.
    val minPrice: StateFlow<Double?> = _minPrice

    // Declaración de una variable de estado para el precio máximo (nullable).
    private val _maxPrice = MutableStateFlow<Double?>(null)
    // Exposición de la variable de estado _maxPrice como un StateFlow para ser observada externamente.
    val maxPrice: StateFlow<Double?> = _maxPrice

    // Función para establecer el valor del precio mínimo.
    fun setMinPrice(price: Double?) {
        _minPrice.value = price // Asigna el valor proporcionado a la variable de estado _minPrice.
    }

    // Función para establecer el valor del precio máximo.
    fun setMaxPrice(price: Double?) {
        _maxPrice.value = price // Asigna el valor proporcionado a la variable de estado _maxPrice.
    }

    fun setSinProductosDialogFalse() {
        _sinProductosDialog.value = false
        println("ESTADO DEL DIALOGO: ${_sinProductosDialog.value}")
    }

}