package com.example.cinema.film

data class RaterMOvData (
    var results: List<Result>
)

data class Result(
    val original_title : String,
    val release_date: String,
    val id : Int
)




