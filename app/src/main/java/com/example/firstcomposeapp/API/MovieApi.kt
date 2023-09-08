package com.example.firstcomposeapp.API

import com.example.firstcomposeapp.model.DetailResponse
import com.example.firstcomposeapp.model.Details
import com.example.firstcomposeapp.model.MovieResponse
import com.example.firstcomposeapp.model.NowPlaying
import com.example.firstcomposeapp.model.Upcoming
import com.example.firstcomposeapp.model.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjEyYTBjNWZkYWE2ODczYjkxZDNkMjE1ZDNlMTBmZCIsInN1YiI6IjY0ZTgxNDg1ZTg5NGE2MDBjNzI4MzY0NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cAie5h0XuUFQf4G06yrVlV5mbbgmJSbTOCFqZGmmZ_E")
    @GET("3/movie/now_playing")
    fun getMovies(@Query("language") language: String, @Query("page") page: Int): Call<MovieResponse>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjEyYTBjNWZkYWE2ODczYjkxZDNkMjE1ZDNlMTBmZCIsInN1YiI6IjY0ZTgxNDg1ZTg5NGE2MDBjNzI4MzY0NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cAie5h0XuUFQf4G06yrVlV5mbbgmJSbTOCFqZGmmZ_E")
    @GET("3/movie/{movie_id}")
    fun getDetails(@Path("movie_id") movieId: Int): Call<Details>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjEyYTBjNWZkYWE2ODczYjkxZDNkMjE1ZDNlMTBmZCIsInN1YiI6IjY0ZTgxNDg1ZTg5NGE2MDBjNzI4MzY0NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cAie5h0XuUFQf4G06yrVlV5mbbgmJSbTOCFqZGmmZ_E")
    @GET("3/movie/upcoming")
    fun getUpcoming(@Query("language") language: String, @Query("page") page: Int): Call<UpcomingResponse>

}