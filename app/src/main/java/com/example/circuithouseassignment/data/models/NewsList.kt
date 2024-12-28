package com.example.circuithouseassignment.data.models

data class NewsList(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)