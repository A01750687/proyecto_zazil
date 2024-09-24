package com.ars.zazil.Model

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAppApi {

    @FormUrlEncoded
    @POST("loginApp/")
    fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<LoginResponse>
    data class LoginResponse(val message:String,val userId:Int)
}