package com.ysanjeet535.newsbox.ui.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ysanjeet535.newsbox.ui.theme.RedBoxMedium

@Preview
@Composable
fun CardButtons(
    onSaveLater : ()-> Unit = {},
    onOpen : ()-> Unit = {},
    isOnProfileScreen:Boolean = false,
    onDelete : ()-> Unit = {}
){
    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isOnProfileScreen){
            Button(
                onClick = {
                          //onDelete will come here
                          onDelete()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f)
            ) {
                Row {
                    Text(text = "Remove",style = MaterialTheme.typography.body1)
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            }
        }else{
            Button(
                onClick = { onSaveLater() },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f)
            ) {
                Row {
                    Text(text = "Read Later",style = MaterialTheme.typography.body1)
                    Icon(Icons.Default.List, contentDescription = null)
                }
            }
        }

        Button(
            onClick = { onOpen() },
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .weight(1f)
        ) {
            Row {
                Text(text = "Open",style = MaterialTheme.typography.body1)
                Icon(Icons.Default.ExitToApp, contentDescription = null)
            }
        }

    }

}