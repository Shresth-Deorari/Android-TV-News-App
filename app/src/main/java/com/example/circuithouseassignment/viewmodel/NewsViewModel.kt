package com.example.circuithouseassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuithouseassignment.data.repository.NewsRepository
import com.example.circuithouseassignment.models.NewsList
import com.example.circuithouseassignment.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val headlines: MutableLiveData<Resource<NewsList>> = MutableLiveData()
    private var headlinesPage = 1
    private var headlinesResponse: NewsList? = null

    private val searchNews: MutableLiveData<Resource<NewsList>> = MutableLiveData()
    private var searchNewsPage = 1
    private var searchNewsResponse: NewsList? = null
    private var newSearchQuery: String? = null
    private var oldSearchQuery: String? = null

    fun getHeadlines(country: String) = viewModelScope.launch {
        fetchHeadlines(country)
    }

    private suspend fun fetchHeadlines(country: String) {
        headlines.postValue(Resource.Loading())
        try {
            val response = newsRepository.getHeadlines(country, headlinesPage)
            headlines.postValue(handleHeadlinesResponse(response))
        } catch (e: IOException) {
            headlines.postValue(Resource.Error("Network failure: Unable to connect to the server."))
        } catch (e: Exception) {
            headlines.postValue(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
        }
    }

    fun searchNews(query: String) = viewModelScope.launch {
        fetchSearchNews(query)
    }

    private suspend fun fetchSearchNews(query: String) {
        newSearchQuery = query
        searchNews.postValue(Resource.Loading())
        try {
            val response = newsRepository.searchNews(query, searchNewsPage)
            searchNews.postValue(handleSearchNewsResponse(response))
        } catch (e: IOException) {
            searchNews.postValue(Resource.Error("Network failure: Unable to connect to the server."))
        } catch (e: Exception) {
            searchNews.postValue(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
        }
    }

    private fun handleHeadlinesResponse(response: Response<NewsList>): Resource<NewsList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                headlinesPage++
                if (headlinesResponse == null) {
                    headlinesResponse = resultResponse
                } else {
                    val oldArticles = headlinesResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(headlinesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsList>): Resource<NewsList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                } else {
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
