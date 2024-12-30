package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text

enum class NewsCategory(val title: String?) {
    GENERAL("General"),
    BUSINESS("Business"),
    TECHNOLOGY("Technology"),
    ENTERTAINMENT("Entertainment"),
    SPORTS("Sports"),
    SCIENCE("Science"),
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewsCategoryTabs(
    modifier: Modifier,
    selectedCategory: NewsCategory,
    onCategorySelected: (NewsCategory) -> Unit,
) {
    val categories = remember { NewsCategory.entries.toTypedArray() }
    var selectedIndex by remember { mutableStateOf(categories.indexOf(selectedCategory)) }
    var focusedIndex by remember { mutableStateOf(selectedIndex) }

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = { tabPositions, doesTabRowHaveFocus ->
            if(selectedIndex != focusedIndex){
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tabPositions[selectedIndex],
                    doesTabRowHaveFocus = doesTabRowHaveFocus,
                    activeColor = Color.Black,
                    inactiveColor = Color.Black,
                )
            }
            TabRowDefaults.PillIndicator(
                currentTabPosition = tabPositions[focusedIndex],
                doesTabRowHaveFocus = doesTabRowHaveFocus,
                activeColor = Color(0xFF6E6E6E),
                inactiveColor = Color(0x8D525252),
            )
        },
        modifier = modifier
            .focusRestorer()
            .padding(top = 24.dp),
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onFocus = { focusedIndex = index },
                onClick = {
                    selectedIndex = index
                    onCategorySelected(category)
                },
                colors = TabDefaults.pillIndicatorTabColors(),
            ) {
                Text(
                    text = category.title ?: "All",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    color = Color.White
                )
            }
        }
    }
}