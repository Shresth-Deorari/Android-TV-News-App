package com.example.circuithouseassignment.data.network

import retrofit2.http.GET

interface NewsApi {
    @GET("https://newsapi.org")
    suspend fun getHeadlines(

    )
}