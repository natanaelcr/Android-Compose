package com.example.firstcomposeapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Result {
    @SerializedName("adult")
    @Expose
    private val adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
    private val backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    private val genreIds: List<Int>? = null

    @SerializedName("id")
    @Expose
    private val id: Int? = null

    @SerializedName("original_language")
    @Expose
    private val originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    private val originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    private val overview: String? = null

    @SerializedName("popularity")
    @Expose
    private val popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
    private val posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    private val releaseDate: String? = null

    @SerializedName("title")
    @Expose
    private val title: String? = null

    @SerializedName("video")
    @Expose
    private val video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    private val voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
    private val voteCount: Int? = null
}