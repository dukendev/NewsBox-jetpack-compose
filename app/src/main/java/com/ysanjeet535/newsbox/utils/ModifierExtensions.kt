package com.ysanjeet535.newsbox.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.customToBottom() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    layout(constraints.maxWidth,constraints.maxHeight){
        placeable.placeRelative(0,constraints.maxHeight-placeable.height)
    }
}