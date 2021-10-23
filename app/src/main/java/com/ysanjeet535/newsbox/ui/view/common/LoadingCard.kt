package com.ysanjeet535.newsbox.ui.view.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
                ShimmerBox {
                    ShimmerBoxContent(brush = it)
                }
            }
        }
    }
}

@Composable
fun CompactLoadingCards(){
    LazyColumn(modifier = Modifier.background(Color.White)){
        repeat(10){
            item {
                ShimmerBox {
                    CompactShimmerBoxContent(brush = it)
                }
            }
        }
    }
}



@Composable
fun ShimmerBox(modifier: Modifier = Modifier,content:@Composable() (brush:Brush)->Unit){
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

    content(brush)
}

@Preview
@Composable
fun CompactShimmerBoxContent(brush: Brush = Brush.linearGradient(listOf(Color.Red,Color.Green))){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(brush)
        )
        Column {
            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .width(200.dp)
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(16.dp)
                .width(200.dp)
                .background(brush)
            )
        }

    }
}

@Preview
@Composable
fun ShimmerBoxContent(brush: Brush = Brush.linearGradient(listOf(Color.Red,Color.Green))){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(360.dp)
            .height(540.dp)
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
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )

            Spacer(modifier = Modifier
                .padding(8.dp)
                .height(8.dp)
                .fillMaxWidth()
                .background(brush)
            )


            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Spacer(modifier = Modifier
                    .padding(8.dp)
                    .height(16.dp)
                    .width(100.dp)
                    .background(brush)
                )
                Spacer(modifier = Modifier
                    .padding(8.dp)
                    .height(16.dp)
                    .width(100.dp)
                    .background(brush)
                )
            }


    }
}