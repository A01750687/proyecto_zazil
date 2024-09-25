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
        try {
            return servicio.descargarProducto(id)
        } catch (e: Exception){
            return ProductoApp() 
        }
    }

    suspend fun descargarlistaProducto(): List<ProductoApp>{
        try {
            return servicio.descargarListaProducto()
        } catch (e: Exception){
            return emptyList()
        }
    }

}