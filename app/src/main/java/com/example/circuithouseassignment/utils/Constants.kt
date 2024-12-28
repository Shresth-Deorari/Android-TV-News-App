package com.example.circuithouseassignment.utils

import com.example.circuithouseassignment.BuildConfig

class Constants {
    companion object{
        const val BASE_URL = "https://newsapi.org/"
        const val SEARCH_DELAY = "500L"
        const val QUERY_PAGE_SIZE = "20"
        const val API_KEY = BuildConfig.NEWS_API_KEY
    }
}