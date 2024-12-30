package com.example.circuithouseassignment.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.circuithouseassignment.models.NewsArticle

@Composable
fun BackgroundImageAndText(
    modifier: Modifier,
    focusedArticle: NewsArticle?
){
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        GlideImage(
            imageUrl = focusedArticle?.urlToImage,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                            Color.Transparent
                        ),
                        start = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY),
                        end = androidx.compose.ui.geometry.Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent,
                            Color.Transparent,
                            MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                "Top Headlines",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = buildAnnotatedString {
                    val title = focusedArticle?.title ?: ""
                    if (title.isNotEmpty() && title.firstOrNull()?.isLetterOrDigit() == true) {
                        withStyle(style = SpanStyle(fontSize = 30.sp, color = Color(0xFF9C27B0), fontWeight = FontWeight.Medium)) {
                            append(title.first())
                        }
                        withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)) {
                            append(title.drop(1))
                        }
                    } else {
                        withStyle(style = SpanStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Medium)) {
                            append(title)
                        }
                    }
                },
                fontWeight = FontWeight.Medium,
                modifier = Modifier.width(550.dp)
            )

            Spacer(Modifier.height(15.dp))
            Text(
                text = focusedArticle?.description?:"",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.LightGray,
                modifier = Modifier.width(650.dp),
                maxLines = 2
            )
        }
    }
}