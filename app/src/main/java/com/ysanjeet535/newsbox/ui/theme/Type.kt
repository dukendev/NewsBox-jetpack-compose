package com.ysanjeet535.newsbox.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ysanjeet535.newsbox.R

// Set of Material typography styles to start with

val futuraFont = FontFamily(
    Font(R.font.futurabold,weight = FontWeight.Bold),
    Font(R.font.futurabook,weight = FontWeight.Light),
    Font(R.font.futuraheavy,weight = FontWeight.ExtraBold),
    Font(R.font.futuramedium,weight = FontWeight.Medium,style = FontStyle.Italic)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = futuraFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = futuraFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp
    ),

    h2 = TextStyle(
        fontFamily = futuraFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = futuraFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)