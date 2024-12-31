package com.example.circuithouseassignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.circuithouseassignment.models.NewsArticle
import com.example.circuithouseassignment.ui.components.BackgroundImageAndText
import com.example.circuithouseassignment.ui.components.CustomCard
import com.example.circuithouseassignment.ui.components.NewsCategory
import com.example.circuithouseassignment.ui.components.NewsCategoryTabs
import com.example.circuithouseassignment.ui.components.ShimmerHomeScreen
import com.example.circuithouseassignment.utils.Resource
import com.example.circuithouseassignment.viewmodel.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier, newsViewModel: NewsViewModel) {
    val headlinesResource by newsViewModel.headlines.observeAsState(Resource.Loading())
    var focusedArticle by remember { mutableStateOf<NewsArticle?>(null) }
    var selectedCategory by rememberSaveable { mutableStateOf(NewsCategory.GENERAL) }
    val newsListFocusRequester = remember { FocusRequester() }
    var keyPressJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(selectedCategory) {
        newsViewModel.getHeadlines("us",  selectedCategory.title)
    }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .onPreviewKeyEvent { keyEvent ->
                when {
                    keyEvent.key == Key.DirectionDown -> {
                        when (keyEvent.type) {
                            KeyEventType.KeyDown -> {
                                keyPressJob = scope.launch {
                                    delay(500)
                                    newsViewModel.getHeadlines("us", selectedCategory.title)
                                }
                                false
                            }
                            KeyEventType.KeyUp -> {
                                keyPressJob?.cancel()
                                keyPressJob = null
                                false
                            }
                            else -> false
                        }
                    }
                    else -> false
                }
            }
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
                        .padding(start = 58.dp, bottom = 58.dp)
                ) {

                    NewsCategoryTabs(
                        Modifier.weight(0.45f),
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            if (selectedCategory != category) {
                                selectedCategory = category
                            }
                        },
                    )

                    BackgroundImageAndText(Modifier.weight(2f), focusedArticle)

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(15.dp),
                        state = rememberLazyListState(),
                        modifier = Modifier.focusRequester(newsListFocusRequester)
                    ) {
                        items(
                            items = articles,
                            key = { article ->
                                article.url
                            }
                        ) { article ->
                            if(article.title!="[Removed]"){
                                Spacer(modifier = Modifier.width(8.dp))
                                CustomCard(
                                    article = article,
                                    modifier = Modifier,
                                    onFocusChange = { isFocused ->
                                        if (isFocused) {
                                            focusedArticle = article
                                        }
                                    },
                                    context
                                )
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