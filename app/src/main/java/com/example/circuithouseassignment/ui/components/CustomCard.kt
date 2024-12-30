package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import com.example.circuithouseassignment.models.NewsArticle

@Composable
fun CustomCard(
    article: NewsArticle,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {}
) {
    Card(
        onClick = {

        },
        modifier = modifier
            .width(220.dp)
            .height(120.dp)
            .onFocusChanged { state ->
                onFocusChange(state.isFocused)
            },
        shape = CardDefaults.shape(shape = RoundedCornerShape(8.dp)),
        border = CardDefaults.border(
            border = Border(
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            ),
            focusedBorder = Border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(8.dp)
            )
        ),
        colors = CardDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        ),
        scale = CardDefaults.scale(
            focusedScale = 1.1f
        ),
    ) {
        GlideImage(
            imageUrl = article.urlToImage,
            modifier = Modifier.fillMaxSize()
        )
    }
}

