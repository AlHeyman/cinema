package com.example.cinema

data class CreateRequestTokenData(
    var success: Boolean,
    val expires_at: String,
    var request_token: String

)