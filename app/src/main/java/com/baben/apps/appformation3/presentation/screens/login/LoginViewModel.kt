package com.baben.apps.appformation3.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginRepository.LoginResult>()
    val loginResult: LiveData<LoginRepository.LoginResult> = _loginResult

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    fun login(username: String, password: String) : LoginRepository.LoginResult {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val result = LoginRepository.LoginResult.SUCCESS
            _loginResult.value = result
            this.username.value = username
            this.password.value = password
            return result
        }
        return LoginRepository.LoginResult.AUTH_ERROR
    }


}
