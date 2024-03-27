package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences

class AuthLocalStorage(private val context: Context){


    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    val authTokenKey = "auth_token"

    fun saveToken(token: String?): Boolean {
        if (token != null) {
            sharedPreferences.edit().putString(authTokenKey, token).apply()
            return true
        }
        return false
    }


    fun getToken(): String? {
        return sharedPreferences.getString(authTokenKey, null)
    }



    fun isLogged(): Boolean {
        return getToken() != null
    }


}