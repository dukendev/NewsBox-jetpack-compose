package com.ysanjeet535.newsbox.ui.view.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.ysanjeet535.newsbox.ui.navigation.NavigationComponent
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.viewmodel.MainViewModel
import kotlinx.coroutines.delay

//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@ExperimentalFoundationApi
//@Composable
//fun MainScreen(navController: NavController,paddingValues: Dp, mainViewModel: MainViewModel){
//
//    var showSplashScreen by remember { mutableStateOf(true)}
//    if(showSplashScreen){
//        SplashScreen(onTimeout = { showSplashScreen = false})
//    } else {
//        NavigationComponent(navController = navController, paddingValues = paddingValues, mainViewModel = mainViewModel)
//    }
//
//}

@Composable
fun SplashScreen(onTimeout: () -> Unit){
     val currentTimeOut by rememberUpdatedState(onTimeout)
    LaunchedEffect(true){
        delay(3000)
        currentTimeOut()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(RedBoxDark),
        contentAlignment = Alignment.Center
    ){
        Icon(Icons.Default.AccountBox, contentDescription = "logo")
    }

}