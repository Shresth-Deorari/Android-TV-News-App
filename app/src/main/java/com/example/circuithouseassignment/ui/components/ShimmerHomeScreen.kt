package com.example.circuithouseassignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.example.circuithouseassignment.utils.shimmerEffect

@Composable
fun ShimmerHomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 58.dp, bottom = 58.dp)
    ) {
        Spacer(Modifier.weight(0.45f))
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerEffect()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(80.dp))
                Box(
                    modifier = Modifier
                        .width(800.dp)
                        .height(30.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .width(600.dp)
                        .height(16.dp)
                        .shimmerEffect()
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(6) { // Show 6 shimmer cards
                ShimmerCard()
                if (it == 0) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
private fun ShimmerCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(220.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmerEffect()
    )
}