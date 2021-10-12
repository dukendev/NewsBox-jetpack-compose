package com.ysanjeet535.newsbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ysanjeet535.newsbox.ui.custom_components.BottomNavBar
import com.ysanjeet535.newsbox.ui.navigation.NavigationComponent
import com.ysanjeet535.newsbox.ui.navigation.Screens
import com.ysanjeet535.newsbox.ui.theme.NewsBoxTheme
import com.ysanjeet535.newsbox.ui.view.home.HomeScreenContent


class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            NavigationComponent(navController = navController,it.calculateBottomPadding())
                        
                    }


                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsBoxTheme {
        HomeScreenContent(30.dp)
    }
}