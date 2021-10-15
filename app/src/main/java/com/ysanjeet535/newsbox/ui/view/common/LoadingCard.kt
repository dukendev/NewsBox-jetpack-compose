package com.ysanjeet535.newsbox.ui.view.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ShimmerBox(modifier: Modifier = Modifier){
    val colors = listOf(Color.LightGray.copy(alpha = 0.4f), Color.LightGray, Color.DarkGray)
    val infiniteTransition = rememberInfiniteTransition()
    val brushTranslateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1500f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500,easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(100f,100f),
        end = Offset(brushTranslateAnimation,brushTranslateAnimation)
    )

    Box(
        modifier = modifier
            .background(brush)
    ) {

    }
}