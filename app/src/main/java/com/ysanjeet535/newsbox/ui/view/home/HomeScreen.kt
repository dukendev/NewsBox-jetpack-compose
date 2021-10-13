package com.ysanjeet535.newsbox.ui.view.home


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ysanjeet535.newsbox.R
import com.ysanjeet535.newsbox.data.remote.dto.Article
import com.ysanjeet535.newsbox.ui.theme.RedBoxDark
import com.ysanjeet535.newsbox.ui.theme.RedBoxMedium
import com.ysanjeet535.newsbox.viewmodel.MainViewModel



@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(paddingValues: Dp,mainViewModel: MainViewModel){

    val scrollState = rememberScrollState()
    val templist by mainViewModel.newsResponse.observeAsState()
    val articles = templist?.articles


    Column(modifier = Modifier
        .padding(bottom = paddingValues)
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(scrollState, enabled = true),
    ) {
        WelcomeText(
            username = "Barney",
            Modifier.padding(16.dp)
        )
        HeadingText(heading = "Top Headlines",Modifier.padding(16.dp))
        CountryCodeDropDown(mainViewModel)
        articles?.let {
            articlesList->
            LazyRow{
                items(articlesList.size){
                    NewsItemCard(newsItem = articlesList[it])
                }
            }
        }

        HeadingText(heading = "Topics",Modifier.padding(16.dp))
        //TopicGrid() //adding topic row instead to make this column vertical scroll possible
        TopicRow()
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
            itemIndex->
            TopicCardItem(topic = topic[itemIndex])
        }
    }
}

@Preview
@Composable
fun TopicRow(){
    val topic = listOf("Sports","Entertainment","Politics","Economy","Bollywood","Crypto","Movies")
    LazyRow(modifier = Modifier.height(100.dp)){
        items(topic.size){
            itemIndex->
            TopicCardItem(topic = topic[itemIndex],aspectRatio = 2f)
        }
    }

}

@Preview
@Composable
fun TopicCardItem(topic : String="Hello",aspectRatio : Float = 1f){
    BoxWithConstraints (
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(aspectRatio)
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
        val mediumShapePoint5 = Offset(width.times(0.9f),height.times(0.5f))

        val mediumPath = Path().apply {
            moveTo(mediumShapePoint1.x,mediumShapePoint1.y)
            quadraticBezierTo(
                mediumShapePoint2.x,mediumShapePoint2.y,
                mediumShapePoint3.x,mediumShapePoint3.y

            )
            quadraticBezierTo(
                mediumShapePoint3.x,mediumShapePoint3.y,
                mediumShapePoint4.x,mediumShapePoint4.y
            )
            quadraticBezierTo(
               mediumShapePoint4.x,mediumShapePoint4.y,
                mediumShapePoint5.x,mediumShapePoint5.y
            )

            lineTo(width.toFloat(),height.toFloat())
            lineTo(-0f,height.toFloat())
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize() ){
            drawPath(mediumPath, RedBoxMedium)
            drawCircle(color = RedBoxMedium,radius = 5f,center = this.center)
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
fun NewsItemCard(newsItem: Article){
    //for web url
//    val context = LocalContext.current
//    val webIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url)) }
    //this uri handler method is simpler than using traditional intents
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
            .height(500.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    topStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .clickable { uriHandler.openUri(newsItem.url) },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column {
            Image(
                painter = rememberImagePainter(data = newsItem.urlToImage){
                      error(R.drawable.ic_launcher_background)
                },
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .height(200.dp)
                    .fillMaxWidth()
                ,
                contentScale = ContentScale.Crop
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



@Composable
fun CountryCodeDropDown(mainViewModel: MainViewModel){
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("us","ca","in","ru","fr")
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .wrapContentSize(Alignment.TopStart)
        .background(Color.LightGray)

    ){
        Text(
            text = items[selectedIndex],
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        expanded = false
                        mainViewModel.updateCountryCode(s)
                        mainViewModel.getTopheadlines()
                    }
                ){
                    Text(text = s)
                }
            }
        }
        
    }

}







