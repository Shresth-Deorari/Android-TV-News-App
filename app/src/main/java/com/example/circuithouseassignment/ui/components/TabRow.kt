package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    selectedCategory: NewsCategory,
    onCategorySelected: (NewsCategory) -> Unit
) {
    val categories = remember { NewsCategory.entries.toTypedArray() }
    var selectedIndex = remember{
        categories.indexOf(selectedCategory)
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = { tabPositions, doesTabRowHaveFocus ->
            TabRowDefaults.PillIndicator(
                currentTabPosition = tabPositions[selectedIndex],
                doesTabRowHaveFocus = doesTabRowHaveFocus,
                activeColor = Color.Red,
                inactiveColor = Color.Cyan
            )
        },
        modifier = Modifier
            .focusRestorer()
            .padding(top = 24.dp),
        contentColor = Color.Black,
        separator = {TabRowDefaults.TabSeparator()},
        containerColor = Color.White
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onFocus = {
                    selectedIndex = index
                },
                onClick = {
                    onCategorySelected(categories[selectedIndex])
                },
                colors = TabDefaults.pillIndicatorTabColors(),

            ) {
                Text(
                    text = category.title?:"All",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}