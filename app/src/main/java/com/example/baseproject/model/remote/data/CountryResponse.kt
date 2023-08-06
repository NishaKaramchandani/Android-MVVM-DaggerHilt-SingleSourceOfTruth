package com.example.baseproject.model.remote.data

import com.google.gson.annotations.SerializedName

/**
 * Data class to represent country from the remote data source layer
 */
data class CountryResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("region")
    val region: String
)
