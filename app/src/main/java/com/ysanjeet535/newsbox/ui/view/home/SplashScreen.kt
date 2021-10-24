package com.ysanjeet535.newsbox.ui.view.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.google.accompanist.glide.rememberGlidePainter
import com.ysanjeet535.newsbox.R
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.ui.theme.RedBoxMedium
import kotlinx.coroutines.delay

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
        SplashScreenLogo()
    }

}


@Preview
@Composable
fun SplashScreenLogo(){
    val radius = remember {Animatable(10f)}
    LaunchedEffect(true){
        radius.animateTo(targetValue = 1000f,animationSpec = tween(2000))
    }

    Box(
        modifier = Modifier
            .size(150.dp)
            .drawBehind {
                drawCircle(
                    radius = radius.value,
                    color = RedBoxMedium,
                    center = Offset(
                        this.size.width
                            .times(0.2)
                            .toFloat(),
                        this.size.height
                            .times(0.2)
                            .toFloat()
                    )
                )
                drawCircle(
                    radius = radius.value,
                    color = RedBoxMedium,
                    center = Offset(
                        this.size.width
                            .times(0.8)
                            .toFloat(),
                        this.size.height
                            .times(0.8)
                            .toFloat()
                    )
                )
                drawCircle(
                    radius = radius.value,
                    color = RedBoxMedium,
                    center = Offset(
                        this.size.width
                            .times(0.2)
                            .toFloat(),
                        this.size.height
                            .times(0.8)
                            .toFloat()
                    )
                )
                drawCircle(
                    radius = radius.value,
                    color = RedBoxMedium,
                    center = Offset(
                        this.size.width
                            .times(0.8)
                            .toFloat(),
                        this.size.height
                            .times(0.2)
                            .toFloat()
                    )
                )
            },
        contentAlignment = Alignment.Center,

    ){
        Image(
            painter = rememberGlidePainter(request = R.mipmap.ic_launcher),
            contentDescription = "logo",
            Modifier.scale(1+ radius.value.times(0.001).toFloat())
        )
    }

}