package com.baben.apps.appformation3.presentation.screens.login

import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepository : ApiLoginRepository

    @Before
    fun setUp() {
        loginRepository = ApiLoginRepository()
        loginViewModel = LoginViewModel(loginRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun login_With_ValidInput() {
        val username = "youssra.rochdi@gmail.com"
        val password = "30082000"
        val result = loginViewModel.login(username, password)
        assertEquals(LoginRepository.LoginResult.SUCCESS ,result)

    }


    @Test
    fun login_With_InvalidInput() {
        val username = ""
        val password = ""
        val result = loginViewModel.login(username, password)
        assertEquals(LoginRepository.LoginResult.AUTH_ERROR, result)
    }
}