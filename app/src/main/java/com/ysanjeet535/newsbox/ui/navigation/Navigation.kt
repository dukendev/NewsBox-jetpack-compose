package com.ysanjeet535.newsbox.ui.navigation

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ysanjeet535.newsbox.ui.view.explore.ExploreScreenContent
import com.ysanjeet535.newsbox.ui.view.home.HomeScreenContent
import com.ysanjeet535.newsbox.ui.view.profile.ProfileScreenContent
import com.ysanjeet535.newsbox.viewmodel.MainViewModel

@ExperimentalFoundationApi
@Composable
fun NavigationComponent(navController: NavController,paddingValues: Dp,mainViewModel: MainViewModel
){


    NavHost(navController = navController as NavHostController, startDestination = Screens.Home.name){
        composable(Screens.Home.name){
            HomeScreenContent(paddingValues = paddingValues,mainViewModel = mainViewModel)
        }

        composable(Screens.Explore.name){
            ExploreScreenContent()
        }

        composable(Screens.Profile.name){
            ProfileScreenContent(paddingValues = paddingValues)
        }
    }
}
