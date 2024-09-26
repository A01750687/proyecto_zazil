package com.ars.zazil.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicioRemoto {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.48.79.109:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Objeto específico para descargar un dato(servicio)
    private val servicio by lazy {
        retrofit.create(ProductoAppApi::class.java)
    }

    suspend fun descargarProducto(id:String): ProductoApp{
        var producto: ProductoApp
        try {
            producto = servicio.descargarProducto(id)
        } catch (e: Exception){
            producto = ProductoApp()
        }
        return producto
    }

    suspend fun descargarlistaProducto(): List<ProductoApp>{
        var listaProductos: List<ProductoApp>
        try {
            listaProductos = servicio.descargarListaProducto()
        } catch (e: Exception){
            listaProductos =  emptyList()
        }
        return listaProductos
    }

}