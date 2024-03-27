package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.data.remote.models.UserDto
import com.baben.apps.appformation3.domain.models.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: Login): Response<Login>

    @POST("auth/signup")
    suspend fun signUp(@Body signUpRequest: UserDto): Response<UserDto>
}