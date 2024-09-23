package com.ars.zazil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.zazil.Model.ProductoApp
import com.ars.zazil.Model.ServicioRemoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoAppVM: ViewModel() {

    private val servicioRemoto = ServicioRemoto()

    private val _estadoLista = MutableStateFlow(listOf<ProductoApp>())
    val estadoLista: StateFlow<List<ProductoApp>> = _estadoLista



    fun descargarListaProducto(){
        viewModelScope.launch {
            val listaProducto = servicioRemoto.descargarlistaProducto()
            _estadoLista.value = listaProducto
        }
    }

}