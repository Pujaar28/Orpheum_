package com.binargrouptwo.orpheum.model.networking.auth

import com.google.gson.annotations.SerializedName

data class Token(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
    )