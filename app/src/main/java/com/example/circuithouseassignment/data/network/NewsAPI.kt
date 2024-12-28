package com.example.circuithouseassignment.data.network

import com.example.circuithouseassignment.models.NewsList
import com.example.circuithouseassignment.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode : String = "in",
        @Query("Page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ): Response<NewsList>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("query")
        searchQuery : String,
        @Query("Page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ): Response<NewsList>
}