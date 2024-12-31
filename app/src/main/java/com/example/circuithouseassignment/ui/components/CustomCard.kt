package com.example.circuithouseassignment.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import com.example.circuithouseassignment.models.NewsArticle

@Composable
fun CustomCard(
    article: NewsArticle,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    context: Context
) {
    Card(
        onClick = {
                try {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(article.url)
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Log.e("CustomCard", "Error opening URL: ${e.message}")
                    Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
                }
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
                shape = RoundedCornerShape(10.dp)
            ),
            focusedBorder = Border(
                border = BorderStroke(
                    width = 2.dp,
                    color = Color(0xAFFFFFFF)
                ),
                shape = RoundedCornerShape(10.dp)
            )
        ),
        colors = CardDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        ),
        scale = CardDefaults.scale(
            focusedScale = 1.2f
        ),
        glow = CardDefaults.glow(focusedGlow = Glow(elevationColor = Color(0xFF513873), elevation = 10.dp))
    ) {
        GlideImage(
            imageUrl = article.urlToImage,
            modifier = Modifier.fillMaxSize()
        )
    }
}

