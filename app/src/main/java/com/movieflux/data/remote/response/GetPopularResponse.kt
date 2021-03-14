package com.movieflux.data.remote.response


import com.google.gson.annotations.SerializedName
import com.movieflux.data.entity.Movie

data class GetPopularResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Movie>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)