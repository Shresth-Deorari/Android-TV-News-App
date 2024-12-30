package com.example.circuithouseassignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.circuithouseassignment.data.repository.NewsRepository
import com.example.circuithouseassignment.ui.theme.CircuitHouseAssignmentTheme
import com.example.circuithouseassignment.viewmodel.NewsViewModel
import com.example.circuithouseassignment.viewmodel.NewsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsRepository = NewsRepository()
        val newsViewModelFactory = NewsViewModelFactory(newsRepository)
        val newsViewModel = ViewModelProvider(this, newsViewModelFactory)[NewsViewModel::class.java]
        setContent {
            CircuitHouseAssignmentTheme {
                    HomeScreen(Modifier.fillMaxSize(), newsViewModel)
            }
        }
    }
}
