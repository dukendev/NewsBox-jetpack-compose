package com.ysanjeet535.newsbox

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.ysanjeet535.newsbox.ui.custom_components.BottomNavBar
import com.ysanjeet535.newsbox.ui.navigation.NavigationComponent
import com.ysanjeet535.newsbox.ui.navigation.Screens
import com.ysanjeet535.newsbox.ui.theme.NewsBoxTheme
import com.ysanjeet535.newsbox.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel by viewModels<MainViewModel>()
        setContent{
            val navController = rememberNavController()
            val currentScreen = mutableStateOf<Screens>(Screens.Home)

            NewsBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    
                    Scaffold(
                        bottomBar = {

                            BottomNavBar(currentScreen = currentScreen.value.name,navController){
                                currentScreen.value = it
                            }
                        }
                    ) {
                            NavigationComponent(
                                navController = navController,
                                paddingValues = it.calculateBottomPadding(),
                                mainViewModel = mainViewModel
                            )
                        
                    }


                }
            }
        }
    }
}



