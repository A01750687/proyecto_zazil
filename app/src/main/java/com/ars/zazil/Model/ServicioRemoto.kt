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

    suspend fun descargarlistaProducto(): List<ProductoApp>{
        return servicio.descargarListaProducto()
    }

}