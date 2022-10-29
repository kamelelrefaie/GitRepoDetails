package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.presentaion.navigation.Screen
import com.example.juniorandroiddevelopertask.presentaion.theme.*

val bgColorList = listOf(
    Green, Yellow
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoItem(repo: GitHubRepo, navController: NavHostController = rememberNavController()) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(Screen.RepoDetail.repoDetailWithId(repo.repoId))
            }, colors = CardDefaults.cardColors(containerColor = if (repo.forksCount > 50) bgColorList[0] else bgColorList[1])
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(20.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

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
                    shape = RoundedCornerShape(8.dp),
                    label = { Text(text = "${repo.stargazersCount}") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }

            Text(
                text = repo.repoName,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )

            Text(
                text = repo.description,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}