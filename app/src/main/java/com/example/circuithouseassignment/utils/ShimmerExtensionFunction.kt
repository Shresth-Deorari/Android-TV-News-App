package com.example.circuithouseassignment.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerEffect(): Modifier = composed {
    var value by remember { mutableStateOf(0f) }
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )
    value = translateAnim.value

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF5A4A72).copy(alpha = 0.6f),  // Soft purple glow
                Color(0xFF2F2236).copy(alpha = 0.2f),  // Darker shade to blend into background
                Color(0xFF5A4A72).copy(alpha = 0.6f),
            ),
            start = Offset(value - 1000, value - 1000),
            end = Offset(value, value),
        )
    )
}