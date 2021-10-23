package com.ysanjeet535.newsbox.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.ysanjeet535.newsbox.R

@Preview
@Composable
fun NoInternetBox(){
    Column {
        Box(modifier = Modifier.size(400.dp)) {


            Image(
                painter = rememberGlidePainter(request = R.raw.ic_no_connection),
                contentDescription = "No Internet",
                modifier = Modifier.background(Color.Transparent).fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }
        
        Text(text = "Please Connect to the Internet",style = MaterialTheme.typography.h2,modifier = Modifier.align(Alignment.CenterHorizontally))
    }
    
}