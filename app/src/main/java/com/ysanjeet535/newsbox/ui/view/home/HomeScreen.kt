package com.ysanjeet535.newsbox.ui.view.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ysanjeet535.newsbox.data.model.NewsItem
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.ui.theme.RedBoxMedium


@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(paddingValues: Dp){
    val newsItem = NewsItem.mock()
    Column(modifier = Modifier
        .padding(bottom = paddingValues)
        .fillMaxSize()
        .background(Color.White),
    ) {
        WelcomeText(
            username = "Barney",
            Modifier.padding(16.dp)
        )
        HeadingText(heading = "Top Headlines",Modifier.padding(16.dp))
        NewsItemCard(newsItem)
        HeadingText(heading = "Topics",Modifier.padding(16.dp))
        TopicGrid()
    }
}

@Composable
fun WelcomeText(username:String,modifier: Modifier = Modifier){
    Text(
        text = "Hello ${username}, Open your NewsBOX!!",
        style = MaterialTheme.typography.h2,
        modifier = modifier,
        color = Color.Black
    )
}

@Composable
fun HeadingText(heading : String,modifier: Modifier = Modifier){
    Text(
        text = heading,
        style = MaterialTheme.typography.h2,
        color = Color.Black,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun TopicGrid(){
    val topic = listOf("Sports","Entertainment","Politics","Economy","Bollywood","Crypto","Movies")
    LazyVerticalGrid(
        cells = GridCells.Fixed(3) ){
        items(topic.size){
            item->
            TopicCardItem(topic = topic[item])
        }
    }
}


@Composable
fun TopicCardItem(topic : String){
    BoxWithConstraints (
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(RedBoxDark)
            ) {
        val width =  constraints.maxWidth
        val height = constraints.maxHeight

        //path points for medium shape path curve
        val mediumShapePoint1 = Offset(width.times(0.0f),height.times(0.3f))
        val mediumShapePoint2 = Offset(width.times(0.1f),height.times(0.35f))
        val mediumShapePoint3 = Offset(width.times(0.4f),height.times(0.05f))
        val mediumShapePoint4 = Offset(width.times(0.7f),height.times(075f))
        val mediumShapePoint5 = Offset(width.times(1.5f),-height.toFloat())

        val mediuamPath = Path().apply {
            moveTo(mediumShapePoint1.x,mediumShapePoint1.y)
            quadraticBezierTo(
                (mediumShapePoint1.x+mediumShapePoint2.x).times(0.5f),
                (mediumShapePoint1.y+mediumShapePoint2.y).times(0.5f),
                mediumShapePoint2.x,
                mediumShapePoint2.y,
            )
            quadraticBezierTo(
                (mediumShapePoint2.x+mediumShapePoint3.x).times(0.5f),
                (mediumShapePoint2.y+mediumShapePoint3.y).times(0.5f),
                mediumShapePoint3.x,
                mediumShapePoint3.y,
            )
            quadraticBezierTo(
                (mediumShapePoint3.x+mediumShapePoint4.x).times(0.5f),
                (mediumShapePoint3.y+mediumShapePoint4.y).times(0.5f),
                mediumShapePoint4.x,
                mediumShapePoint4.y,
            )
            quadraticBezierTo(
                (mediumShapePoint4.x+mediumShapePoint5.x).times(0.5f),
                (mediumShapePoint4.y+mediumShapePoint5.y).times(0.5f),
                mediumShapePoint5.x,
                mediumShapePoint5.y,
            )
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize() ){
            drawPath(mediuamPath, RedBoxMedium)
        }
        
        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = topic,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }



    }
}

@Preview
@Composable
fun TopicCardPreview(){
    TopicCardItem("Local")
}

@Composable
fun NewsItemCard(newsItem: NewsItem){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    topStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column {
            Image(
                painter = rememberImagePainter(data = newsItem.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Text(
                text = newsItem.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h3
            )
            Text(
                text = newsItem.description,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.body1,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsItem.source.name,
                    modifier = Modifier.padding(8.dp)
                )
                val date = remember {
                    newsItem.publishedAt.dropLastWhile { it != 'T' }.dropLast(1)
                }
                Text(
                    text = date,
                    modifier = Modifier.padding(8.dp),
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}

@Preview
@Composable
fun NewsCardPreview(){
    NewsItemCard(newsItem = NewsItem.mock())
}