package com.ars.zazil.Model

class TokenProvider {
    private var token: String? = null

    fun setToken(newToken: String?) {
        token = newToken
    }

    fun getToken(): String? {
        return token
    }
}