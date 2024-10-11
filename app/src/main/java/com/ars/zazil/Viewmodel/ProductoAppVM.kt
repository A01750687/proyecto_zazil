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

    private val _multipleQuery = MutableStateFlow<List<String>>(emptyList())
    val multipleQuery: StateFlow<List<String>> = _multipleQuery

    fun agregarElemento(nuevoElemento: String) {
        _multipleQuery.value = _multipleQuery.value + nuevoElemento
        println(_multipleQuery.value)
    }

    // Función para eliminar un elemento de la lista
    fun eliminarElemento(elemento: String) {
        _multipleQuery.value = _multipleQuery.value - elemento
        println(_multipleQuery.value)
    }

    fun limpiarLista() {
        _multipleQuery.value = emptyList()
        _checkboxStates.value = emptyMap()
        println(_multipleQuery.value)
    }


    fun aplicarFiltros() {
        println("Términos de búsqueda: ${_multipleQuery.value}")
        val listaFiltrada = if (_multipleQuery.value.isEmpty()) {
            _estadoLista.value
        } else {
            _estadoLista.value.filter { producto ->
                _multipleQuery.value.all { query ->
                    producto.nombre.contains(query, ignoreCase = true)
                }
            }
        }
        println("Productos filtrados: ${listaFiltrada.map { it.nombre }}")
        _estadoFiltrado.value = listaFiltrada
    }

    private val _checkboxStates = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val checkboxStates: StateFlow<Map<String, Boolean>> = _checkboxStates

    fun updateCheckboxState(label: String, isChecked: Boolean) {
        _checkboxStates.value = _checkboxStates.value.toMutableMap().apply {
            this[label] = isChecked
        }

        if (isChecked) {
            agregarElemento(label)
        } else {
            eliminarElemento(label)
        }
    }
}