package com.ars.zazil.Model

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductoAppApi {

    @GET("JSONItem/{id}")
    suspend fun descargarProducto(@Path("id") id: String):ProductoApp

    @GET("JSONlistadoItems/")
    suspend fun descargarListaProducto():List<ProductoApp>
}