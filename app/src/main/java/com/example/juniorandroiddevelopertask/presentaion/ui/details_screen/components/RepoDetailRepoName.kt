package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juniorandroiddevelopertask.presentaion.theme.paledark

@Composable
fun RepoDetailContent(repoName:String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Column() {
            Text(
                text =repoName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = paledark,
            )
        }

    }
}
