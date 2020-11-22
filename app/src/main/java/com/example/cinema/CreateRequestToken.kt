package com.example.cinema

data class CreateRequestToken (
    var success: Boolean,
    val expires_at: String,
    var request_token: String

)