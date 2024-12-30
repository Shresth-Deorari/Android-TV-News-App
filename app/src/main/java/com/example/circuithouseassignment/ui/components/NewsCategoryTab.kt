package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text

@Composable
fun NewsCategoryTab() {
    var selectedTabIndex by remember { mutableIntStateOf(0) } // Default to first tab
    val categories = listOf("General", "Business", "Technology", "Sports", "Entertainment")

    println("Debug: selectedTabIndex = $selectedTabIndex, categories size = ${categories.size}")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        separator = {},
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = selectedTabIndex == index,
                onFocus = { selectedTabIndex = index },
                onClick = { selectedTabIndex = index }
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = if (selectedTabIndex == index) Color.White else Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

