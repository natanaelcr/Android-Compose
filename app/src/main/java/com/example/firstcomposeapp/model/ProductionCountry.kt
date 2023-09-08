package com.example.firstcomposeapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountry {
    @SerializedName("iso_3166_1")
    @Expose
    private val iso31661: String? = null

    @SerializedName("name")
    @Expose
    private val name: String? = null
}