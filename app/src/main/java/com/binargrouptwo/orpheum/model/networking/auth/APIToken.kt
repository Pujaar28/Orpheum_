package com.binargrouptwo.orpheum.model.networking.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object APIToken {
        const val BASE_URL_TOKEN = "https://accounts.spotify.com/api/"
        const val GRANT_TYPE = "client_credentials"
        const val CLIENT_ID = "21jzywsmvqjvdbbhmo2k56s4i"
        const val CLIENT_SECRET = "77f51f6acbc24a74b578a58db824f67b"

        val retrofit: APITokenGson by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_TOKEN)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APITokenGson::class.java)
        }

    /*fun new(){
        val client_id = "CLIENT_ID"
        val client_secret = "CLIENT_SECRET"

        val authOptions = mapOf(
            "url" to "https://accounts.spotify.com/api/token",
            "headers" to mapOf(
                "Authorization" to "Basic " + Base64.getEncoder().encodeToString("$client_id:$client_secret".toByteArray())
            ),
            "form" to mapOf(
                "grant_type" to "client_credentials"
            ),
            "json" to true
        )

        request.post(authOptions) { error, response, body ->
            if (!error && response.statusCode === 200) {
                val token = body.access_token
            }
        }
    }*/
}
