package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.presentaion.navigation.Screen
import com.example.juniorandroiddevelopertask.presentaion.theme.paledark
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.bgColorList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoDetailDescription(repo: GitHubRepo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        AssistChip(
            onClick = {},
            shape = RoundedCornerShape(8.dp), label = { Text(text = "${repo.forksCount}") },
            leadingIcon = {
                Icon(
                    Icons.Filled.ForkRight,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
        AssistChip(
            onClick = {},
            shape = RoundedCornerShape(8.dp), label = { Text(text = "${repo.stargazersCount}") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
        AssistChip(
            onClick = {},
            shape = RoundedCornerShape(8.dp), label = { Text(text = "${repo.issuesCount}") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Adjust,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

    }
    Spacer(modifier = Modifier.padding(5.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = repo.description,
                fontSize = 18.sp,
                color = paledark
            )
        }


}

