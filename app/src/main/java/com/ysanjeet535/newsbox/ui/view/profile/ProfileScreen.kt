package com.ysanjeet535.newsbox.ui.view.profile

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ysanjeet535.newsbox.R
import com.ysanjeet535.newsbox.data.model.NewsItem.Companion.mapToArticle
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.ui.view.common.CompactNewsCard
import com.ysanjeet535.newsbox.ui.view.common.ExpandableNewsCard
import com.ysanjeet535.newsbox.ui.view.common.LoadingCards
import com.ysanjeet535.newsbox.ui.view.common.ShimmerBox
import com.ysanjeet535.newsbox.viewmodel.MainViewModel

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ProfileScreenContent(paddingValues: Dp,mainViewModel: MainViewModel){

    val isAlertDialogOpen =  remember{ mutableStateOf(false)}
    val savedItems by mainViewModel.getAllNewsItem().observeAsState()
    Column(
        modifier = Modifier
            .padding(bottom = paddingValues)
            .fillMaxSize()
    ) {
        var numberOfNews = 0
        if(!savedItems.isNullOrEmpty()){ numberOfNews = savedItems!!.size }
        ProfileHeader(int = numberOfNews)
        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, RedBoxDark))){
            Text(
                text = "Your Saved News",
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        Alignment.CenterStart
                    )
            )

            Button(
                onClick = {
                    isAlertDialogOpen.value = true
                          },
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        Alignment.CenterEnd
                    )
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                )
            }
        }
        
        if(savedItems !=null){
            LazyColumn{
                items(savedItems!!){ item->
                    CompactNewsCard(article = item.mapToArticle(item))
                }
            }
        } else {
            Text(text = "You have no saved articles",style = MaterialTheme.typography.body1)
        }

        if(isAlertDialogOpen.value){
            AlertDialog(
                onDismissRequest = { isAlertDialogOpen.value =false },
                title = { Text(text = "Are you sure?")},
                text = { Text(text = "Do you wish to delete all your saved news?")},
                confirmButton = {
                    Button(
                        onClick = {
                            mainViewModel.deleteAllNews()
                            isAlertDialogOpen.value = false
                        }
                    )
                    { Text(text = "Confirm")}
                },
                dismissButton = { Button(onClick = { isAlertDialogOpen.value =false }){ Text(text = "Cancel")}}
            )
        }


    }

}

@Preview
@Composable
fun ProfileHeader(int: Int=0){
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = "https://wallpaperaccess.com/full/547408.jpg"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, RedBoxDark, CircleShape)

        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
           Text(text = "Barney",style = MaterialTheme.typography.h3)
           Text(text = "#${int}",style = MaterialTheme.typography.h3)
        }
    }
}




