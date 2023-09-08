package com.example.firstcomposeapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Genre {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("name")
    @Expose
    val name: String? = null
}