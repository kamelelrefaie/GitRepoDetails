package com.example.juniorandroiddevelopertask.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.juniorandroiddevelopertask.presentaion.navigation.SetupNavGraph
import com.example.juniorandroiddevelopertask.presentaion.theme.Grey
import com.example.juniorandroiddevelopertask.presentaion.theme.JuniorAndroidDeveloperTaskTheme
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.SearchScreen
import com.example.juniorandroiddevelopertask.presentaion.utils.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val status by connectivityManager.observe().collectAsState(
                initial = false
            )
            val navHostController = rememberNavController()
            JuniorAndroidDeveloperTaskTheme(isNetworkAvailable = status) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Grey
                ) {
                    SetupNavGraph(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JuniorAndroidDeveloperTaskTheme {
        Greeting("Android")
    }
}