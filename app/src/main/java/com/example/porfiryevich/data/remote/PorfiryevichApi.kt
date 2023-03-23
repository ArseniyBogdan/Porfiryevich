package com.example.porfiryevich.data.remote

import com.example.porfiryevich.data.models.SequelResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface PorfiryevichApi {
    @POST("./generate/")
    @Headers("Content-Type: application/json")
    suspend fun sequel(@Body registrationBody: RegistrationBody): Response<SequelResponse>
}