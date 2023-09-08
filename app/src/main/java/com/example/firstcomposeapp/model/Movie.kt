package com.example.firstcomposeapp.model

data class Movie(
    val id : Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Number,
    val poster_path: String?
)
