package com.binargrouptwo.orpheum.model.networking

import com.binargrouptwo.orpheum.constants.Constants
import com.binargrouptwo.orpheum.constants.Constants.DUMMY_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {

    private const val BASE_URL = "https://api.spotify.com/v1/"

    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private fun generateToken(){}

    private val headerInterceptor: Interceptor
        get() {
            return Interceptor { chain ->
                val request = chain.request()
                val headerInterceptedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization",DUMMY_TOKEN)
                    .method(request.method, request.body)
                    .build()
                chain.proceed(headerInterceptedRequest)
            }
        }

    /*3 buat client okhttp*/
    private val client = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.addInterceptor(logging)
        //uncomment for implement header interceptor
    .addInterceptor(headerInterceptor)
        .build()

    /*4 buat service builder*/
    val instance: APIService by lazy {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        retrofit.create(APIService::class.java)
    }
}
