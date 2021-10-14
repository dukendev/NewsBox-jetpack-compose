package com.ysanjeet535.newsbox.ui.view.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExploreScreenContent(){
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.Transparent)) {

        SearchBar(modifier = Modifier.fillMaxWidth()){
            //lambda to perform search options
        }
    }
}

@Preview
@Composable
fun SearchBar(modifier: Modifier = Modifier,onSearch: (String)->Unit={}) {

    var text by remember { mutableStateOf("")}
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
                        },
        modifier = modifier,
        label = { Text(text = "Search")},
        maxLines = 1
    )

}