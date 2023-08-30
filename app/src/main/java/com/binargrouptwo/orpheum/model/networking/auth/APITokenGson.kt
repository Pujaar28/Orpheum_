package com.binargrouptwo.orpheum.model.networking.auth

import android.database.Observable
import android.telecom.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APITokenGson {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("api/token")
    fun getToken(
        @Header("Authorization")auth:String,
        @Field(("grant_type")) grantType:String):retrofit2.Call<Token>



}