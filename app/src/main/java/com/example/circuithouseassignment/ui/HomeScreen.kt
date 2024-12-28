package com.example.circuithouseassignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.circuithouseassignment.ui.components.NewsItem
import com.example.circuithouseassignment.ui.components.ShimmerListItem
import com.example.circuithouseassignment.utils.Resource
import com.example.circuithouseassignment.viewmodel.NewsViewModel

@Composable
fun HomeScreen(innerPadding: PaddingValues, newsViewModel: NewsViewModel) {
    val headlinesResource by newsViewModel.headlines.observeAsState(Resource.Loading())
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        newsViewModel.getHeadlines("us")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ) {
        when (val resource = headlinesResource) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        items(10){
                            ShimmerListItem(Modifier.padding(innerPadding))
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
            is Resource.Success -> {
                val articles = resource.data?.articles ?: emptyList()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    state = scrollState
                ) {
                    item {
                        Text(
                            text = "Top Headlines",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(articles.size) { index ->
                        val article = articles[index]
                        NewsItem(article = article)

                        if (index == articles.lastIndex) {
                            LaunchedEffect(Unit) {
                                newsViewModel.getHeadlines("us")
                            }
                        }

                    }
                }
            }
            is Resource.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = resource.message ?: "Error fetching news",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
