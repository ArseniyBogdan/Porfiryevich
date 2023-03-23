package com.example.porfiryevich

import android.annotation.SuppressLint
import com.example.porfiryevich.data.remote.PorfiryevichApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    lateinit var porfiryevichApi: PorfiryevichApi

    init {
        configureRetrofit()
    }

    fun getRetrofitApi(): PorfiryevichApi {
        return porfiryevichApi
    }

    fun setRetrofitApi(porfiryevichApi: PorfiryevichApi) {
        this.porfiryevichApi = porfiryevichApi
    }

    private fun configureRetrofit(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pelevin.gpt.dobro.ai")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        porfiryevichApi = retrofit.create(PorfiryevichApi::class.java)
    }
}