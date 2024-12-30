package com.example.circuithouseassignment.data.repository

import com.example.circuithouseassignment.data.network.RetrofitClient

class NewsRepository {

    suspend fun getHeadlines(country : String, pageNumber : Int, category : String?) =
        RetrofitClient.api.getHeadlines(countryCode = country, pageNumber = pageNumber, category = category)

    suspend fun searchNews(query : String, pageNumber: Int) =
        RetrofitClient.api.searchNews(query, pageNumber)

}