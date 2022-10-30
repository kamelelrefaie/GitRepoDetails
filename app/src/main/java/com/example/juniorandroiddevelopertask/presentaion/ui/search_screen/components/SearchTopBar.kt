package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.juniorandroiddevelopertask.R
import com.example.juniorandroiddevelopertask.presentaion.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(navHostController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_github),
                contentDescription = "Github picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Welcome back",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )

            }
        }

        BadgedBox(badge = { Badge() }, Modifier.clickable {
            navHostController.navigate(Screen.SaveListScreen.route)
        }) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Notifications"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSearchTopBar() {
    MaterialTheme {
        //   SearchTopBar()
    }
}

