package com.example.circuithouseassignment.models

data class NewsArticle(
    val id : Int? = null,
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String,
    val urlToImage: String?
)