package com.example.circuithouseassignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.circuithouseassignment.models.NewsArticle
import com.example.circuithouseassignment.ui.components.*
import com.example.circuithouseassignment.utils.Resource
import com.example.circuithouseassignment.viewmodel.NewsViewModel

@Composable
fun HomeScreen(modifier: Modifier, newsViewModel: NewsViewModel) {
    val headlinesResource by newsViewModel.headlines.observeAsState(Resource.Loading())
    var focusedArticle by remember { mutableStateOf<NewsArticle?>(null) }
    var selectedCategory by rememberSaveable { mutableStateOf(NewsCategory.GENERAL) }

    LaunchedEffect(selectedCategory) {
        newsViewModel.getHeadlines("us",  selectedCategory.title)
    }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val resource = headlinesResource) {
            is Resource.Loading -> {
                ShimmerHomeScreen()
            }

            is Resource.Success -> {
                val articles = resource.data?.articles ?: emptyList()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 58.dp, bottom = 86.dp)
                ) {

                    NewsCategoryTabs(selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            if (selectedCategory != category) {
                                selectedCategory = category
                            }
                        }
                    )

                    BackgroundImageAndText(Modifier.weight(2f), focusedArticle)

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(15.dp),
                        state = rememberLazyListState()
                    ) {
                        items(
                            items = articles,
                            key = { article ->
                                article.url
                            }
                        ) { article ->
                            Spacer(modifier = Modifier.width(8.dp))
                            CustomCard(
                                article = article,
                                modifier = Modifier,
                                onFocusChange = { isFocused ->
                                    if (isFocused) {
                                        focusedArticle = article
                                    }
                                }
                            )
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