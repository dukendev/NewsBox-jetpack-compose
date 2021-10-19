package com.ysanjeet535.newsbox.ui.view.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ysanjeet535.newsbox.data.model.NewsItem.Companion.mapToArticle
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.ui.view.common.CompactNewsCard
import com.ysanjeet535.newsbox.ui.view.common.ExpandableNewsCard
import com.ysanjeet535.newsbox.ui.view.common.LoadingCards
import com.ysanjeet535.newsbox.ui.view.common.ShimmerBox
import com.ysanjeet535.newsbox.viewmodel.MainViewModel

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ProfileScreenContent(paddingValues: Dp,mainViewModel: MainViewModel){

    val savedItems by mainViewModel.newsItemsLiveData.observeAsState()
    savedItems?.let {
        val list = it
        LazyRow{
            items(list){ item->
                CompactNewsCard(article = item.mapToArticle(item))
            }
        }
    }

//    Surface(modifier = Modifier
//        .padding(bottom = paddingValues)
//        .fillMaxSize()
//        .background(Color.White)
//    ) {
//
//
//    }
}

