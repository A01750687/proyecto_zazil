package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.Model.ServicioRemoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoAppVM: ViewModel() {

    private val servicioRemoto = ServicioRemoto()

    private val _estado = MutableStateFlow(ProductoApp())
    val estado: StateFlow<ProductoApp> = _estado

    private val _estadoLista = MutableStateFlow(listOf<ProductoApp>())
    val estadoLista: StateFlow<List<ProductoApp>> = _estadoLista

    private val _estadoFiltrado = MutableStateFlow(listOf<ProductoApp>())
    val estadoFiltrado: StateFlow<List<ProductoApp>> = _estadoFiltrado


    fun descargarProducto(id:String){
        viewModelScope.launch {
            val producto = servicioRemoto.descargarProducto(id)
            _estado.value = producto
        }
    }

    fun descargarListaProducto(){
        viewModelScope.launch {
            val listaProducto = servicioRemoto.descargarlistaProducto()
            _estadoLista.value = listaProducto
            _estadoFiltrado.value = listaProducto
        }
    }

    fun searchProducto(query:String) {
        val listaFiltrada = if (query.isEmpty()) {
            _estadoLista.value
        } else {
            _estadoLista.value.filter { it.nombre.contains(query, ignoreCase = true) }
        }
        _estadoFiltrado.value = listaFiltrada
    }
}