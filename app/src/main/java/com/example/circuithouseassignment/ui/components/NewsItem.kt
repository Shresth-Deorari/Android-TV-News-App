package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.circuithouseassignment.models.NewsArticle

@Composable
fun NewsItem(article: NewsArticle) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        GlideImage(
            imageUrl = article.urlToImage,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 8.dp)
        )

        Text(
            text = article.title ?: "No title available",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "By ${article.author ?: "Unknown"} | Source: ${article.source?.name ?: "Unknown"}",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = article.description ?: "No description available",
            fontSize = 14.sp,
            maxLines = 3,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Published At: ${article.publishedAt ?: "Unknown date"}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}