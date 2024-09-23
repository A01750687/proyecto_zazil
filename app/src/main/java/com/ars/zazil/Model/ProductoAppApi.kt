package com.ars.zazil.Model

import retrofit2.http.GET

interface ProductoAppApi {

    @GET("JSONlistadoItems/")
    suspend fun descargarListaProducto():List<ProductoApp>
}