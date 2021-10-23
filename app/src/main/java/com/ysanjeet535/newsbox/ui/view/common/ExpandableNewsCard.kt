package com.ysanjeet535.newsbox.ui.view.common

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.ui.view.home.NewsItemCard

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ExpandableNewsCard(article: Article = Article.mock(),isOnProfileScreen:Boolean = false,onSaveLater : ()->Unit={},onDelete : ()-> Unit = {}){
    var isExpanded by remember { mutableStateOf(false)}
    Surface(
        color = MaterialTheme.colors.surface,
        onClick = { isExpanded = !isExpanded}
    ) {
        AnimatedContent(
            targetState = isExpanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }

        ) { targetExpanded->
            if(targetExpanded){
                NewsItemCard(newsItem = article,isOnProfileScreen = isOnProfileScreen,onDelete = onDelete,onSaveLater = onSaveLater)
            } else{
                CompactNewsCard(article = article)
            }

        }
        

    }
}