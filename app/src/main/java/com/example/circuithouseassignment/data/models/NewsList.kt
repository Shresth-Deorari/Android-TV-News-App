package com.example.circuithouseassignment.data.models

data class NewsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)