package com.ysanjeet535.newsbox.ui.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ysanjeet535.newsbox.ui.view.common.CompactNewsCard
import com.ysanjeet535.newsbox.ui.view.common.LoadingCards
import com.ysanjeet535.newsbox.ui.view.common.ShimmerBox

@Composable
fun ProfileScreenContent(paddingValues: Dp){

    Surface(modifier = Modifier
        .padding(bottom = paddingValues)
        .fillMaxSize()
        .background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.background(Color.White)){
            repeat(20){
                item {
                    CompactNewsCard()
                }
            }
        }
    }
}

