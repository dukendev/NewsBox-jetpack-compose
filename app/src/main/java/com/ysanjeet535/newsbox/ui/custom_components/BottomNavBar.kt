package com.ysanjeet535.newsbox.ui.custom_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ysanjeet535.newsbox.ui.navigation.Screens
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.ui.theme.RedBoxMedium

@ExperimentalAnimationApi
@Composable
fun BottomNavBar(currentScreen : String,navController: NavController ,onScreenSelected : (Screens)->Unit){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.arguments?.getString(currentScreen)
    val screensList = Screens.Items.listScreens

    Row(
        modifier= Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        it.name == currentScreen
        screensList.forEach {
                BottomNavBarItem(
                    screen = it,
                    isSelected = it.name == currentScreen
                ) {
                    onScreenSelected(it)
                    if(currentDestination != it.name){
                        navController.navigate(it.name){
                            popUpTo(it.name){
                                inclusive =  true
                            }
                        }
                    }
                }
        }

    }

}

@ExperimentalAnimationApi
@Composable
fun BottomNavBarItem(screen : Screens, isSelected : Boolean, onScreenSelected : ()->Unit){

    val background=if (isSelected) RedBoxMedium.copy(alpha = 0.2f) else Color.Transparent
    val contentColor=if (isSelected) RedBoxDark else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onScreenSelected)
    ){
        Row(
            modifier=Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                imageVector = screen.icon,
                contentDescription =null,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
               Text(text = screen.name,color = contentColor)
            }

        }
    }


}

