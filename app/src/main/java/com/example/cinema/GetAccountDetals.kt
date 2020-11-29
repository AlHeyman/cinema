package com.example.cinema

data class GetAccountDetals(
    //val avatar : String,
    val name: String,
    // val username : String,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String, val id: Int
)