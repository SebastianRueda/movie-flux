package com.movieflux.data.remote

import com.movieflux.data.remote.response.GetPopularResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "2432ec485c9fb48ed4e82e6fa6139091"

        @Volatile
        var INSTANCE: MovieService? = null

        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildClient().also { INSTANCE = it }
        }

        private fun buildClient(): MovieService {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(MovieService::class.java)
        }
    }

    interface MovieService {
        @GET("movie/popular?api_key=$API_KEY")
        suspend fun getPopularMovies(@Query("page") page: Int = 1): GetPopularResponse
    }
}