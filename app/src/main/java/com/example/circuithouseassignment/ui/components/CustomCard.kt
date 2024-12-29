package com.example.circuithouseassignment.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.circuithouseassignment.models.NewsArticle
import com.example.circuithouseassignment.ui.components.GlideImage

@Composable
fun SampleScreen(
    innerPadding: PaddingValues,
    article: NewsArticle
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ){
        Text(
            "Top Headlines",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10) {
                Spacer(modifier = Modifier.height(15.dp))
                CustomCard(article)
            }
        }
    }
}

@Composable
fun CustomCard(article: NewsArticle) {
    Card(
        onClick = {},
        modifier = Modifier.fillMaxWidth(0.9f)
            .height(120.dp),
        border =
        CardDefaults.border(
            border = Border(
                border = BorderStroke(width = 1.dp, color = Color(0xFF838383)),
                shape = RoundedCornerShape(5)
            ),
            focusedBorder =
            Border(
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(5),
            ),
        ),
        colors =
        CardDefaults.colors(containerColor = MaterialTheme.colorScheme.background, focusedContainerColor = Color(0x2BDCDCDC)),
        scale =
        CardDefaults.scale(
            focusedScale = 1.05f,
        )
    ) {
        Row(){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
//                    .background(Color.Red)
            ){
                GlideImage(
                    imageUrl = article.urlToImage,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
//                    .background(Color.Blue)
            ){
                NewsDetails(article)
            }
        }
    }
}

@Composable
fun NewsDetails(article: NewsArticle) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            "${article.title}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            maxLines = 2
        )
        Spacer(Modifier.height(5.dp))
        Text(
            "${article.description}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xC8D0D0D0)
        )
    }
}
