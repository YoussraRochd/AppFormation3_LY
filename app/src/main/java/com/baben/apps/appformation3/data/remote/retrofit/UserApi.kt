package com.baben.apps.appformation3.data.remote.retrofit
import com.baben.apps.appformation3.data.remote.models.Name
import com.baben.apps.appformation3.data.remote.models.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/login")
    suspend fun login(@Body loginRequest: Name): Response<Name>

    @POST("/signup")
    suspend fun signUp(@Body signUpRequest: UserDto): Response<UserDto>
}