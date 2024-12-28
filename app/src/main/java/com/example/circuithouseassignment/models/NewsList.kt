package com.example.circuithouseassignment.models

data class NewsList(
    val articles: MutableList<NewsArticle>,
    val status: String,
    val totalResults: Int
)