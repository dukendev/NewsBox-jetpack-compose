package com.ysanjeet535.newsbox.ui.view.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoadingCards(){
    LazyRow(modifier = Modifier.background(Color.White)){
        repeat(10){
            item {
                ShimmerBox()
            }
        }
    }
}


@Preview
@Composable
fun ShimmerBox(modifier: Modifier = Modifier){
    val colors = listOf(Color.DarkGray, Color.LightGray, Color.DarkGray)
    val infiniteTransition = rememberInfiniteTransition()
    val brushTranslateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1500f,
        animationSpec = infiniteRepeatable(
            animation = tween(500,easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(10f,10f),
        end = Offset(brushTranslateAnimation,brushTranslateAnimation)
    )

    ShimmerBoxContent(brush = brush)

}

@Preview
@Composable
fun ShimmerBoxContent(brush: Brush = Brush.linearGradient(listOf(Color.Red,Color.Green))){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
            .height(500.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    topStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(MaterialTheme.colors.background)
        ,
    ){

            Spacer(modifier  = Modifier
                .padding(16.dp)
                .height(200.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .fillMaxWidth()
                .background(brush)
            )


            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .fillMaxWidth()
                .background(brush)
            )

    }
}