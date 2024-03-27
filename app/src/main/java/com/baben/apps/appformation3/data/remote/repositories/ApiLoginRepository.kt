package com.baben.apps.appformation3.data.remote.repositories

import android.accounts.NetworkErrorException
import com.baben.apps.appformation3.data.remote.retrofit.UserApi
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository

class ApiLoginRepository(private val userApi: UserApi) : LoginRepository {

    override suspend fun login(model: Login?): LoginRepository.LoginResult? {
        model?.let {
            try {
                val response = userApi.login(it)
                return if (response.isSuccessful) {
                    LoginRepository.LoginResult.SUCCESS
                } else {
                    LoginRepository.LoginResult.AUTH_ERROR
                }
            } catch (e: Exception) {
                throw NetworkErrorException("Error during login: ${e.message}")
            }
        }
        return LoginRepository.LoginResult.GENERAL_ERROR
    }
}