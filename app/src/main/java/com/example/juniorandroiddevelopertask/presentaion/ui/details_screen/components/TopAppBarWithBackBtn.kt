package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juniorandroiddevelopertask.presentaion.theme.paledark

@Composable
fun TopBarWithBack(
    isSaved: Boolean,
    isLiked: Boolean,
    title: String,
    onBackClick: () -> Unit,
    onSaveClicked: () -> Unit,
    onLovedClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                modifier = Modifier.size(32.dp, 32.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
            )
        }

        Text(
            text = title,
            color = paledark,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )

        Row() {
            Card(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                IconButton(onClick = onSaveClicked) {
                    Icon(
                        imageVector = if (isSaved) Icons.Outlined.Delete else Icons.Outlined.Save,
                        contentDescription = ""
                    )

                }
            }

            Card(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                IconButton(onClick = onLovedClicked) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "",
                        tint = if (isLiked) Color.Red else Color.Black
                    )
                }
            }
        }


    }

}