package com.example.happyplacesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.happyplacesapp.data.HappyPlaceDatabase
import com.example.happyplacesapp.data.HappyPlaceRepository
import com.example.happyplacesapp.ui.navigation.AppNavHost
import com.example.happyplacesapp.ui.theme.HappyPlacesAppTheme
import com.example.happyplacesapp.viewmodel.HappyPlaceViewModel
import com.example.happyplacesapp.viewmodel.HappyPlaceViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Repository & ViewModel-Setup
        val dao = HappyPlaceDatabase.getInstance(applicationContext).happyPlaceDao()
        val repository = HappyPlaceRepository(dao)
        val factory = HappyPlaceViewModelFactory(repository)
        val viewModel: HappyPlaceViewModel by viewModels { factory }

        setContent {
            val navController = rememberNavController()

            HappyPlacesAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
