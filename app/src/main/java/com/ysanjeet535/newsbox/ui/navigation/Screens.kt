package com.ysanjeet535.newsbox.ui.navigation

import android.graphics.drawable.Drawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val name:String,val icon:ImageVector){
    object Home : Screens("home", Icons.Default.Home)
    object Explore : Screens("explore",Icons.Default.Search)
    object Profile : Screens("profile",Icons.Default.Person)

    object Items{
        val listScreens = listOf(
            Home,Explore,Profile
        )
    }
}
