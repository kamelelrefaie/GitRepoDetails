package com.example.juniorandroiddevelopertask.presentaion.common
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean,
) {
    AnimatedVisibility(visible = !isNetworkAvailable) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "No Internet Connection",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = MaterialTheme.typography.labelLarge, color = Color.Red
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}