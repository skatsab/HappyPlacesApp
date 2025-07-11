package com.example.happyplacesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.happyplacesapp.ui.AddPlaceScreen
import com.example.happyplacesapp.ui.DetailScreen
import com.example.happyplacesapp.ui.MainScreen
import com.example.happyplacesapp.ui.MapScreen
import com.example.happyplacesapp.viewmodel.HappyPlaceViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: HappyPlaceViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN,
        modifier = modifier
    ) {
        composable(route = Routes.MAIN) {
            MainScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate(Routes.ADD) },
                onNavigateToDetail = { navController.navigate(Routes.DETAIL) },
                onNavigateToMap = { navController.navigate(Routes.MAP) },
                onItemClick = { viewModel.selectPlace(it) }
            )
        }

        composable(route = Routes.ADD) {
            AddPlaceScreen(
                viewModel = viewModel,
                onSave = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Routes.DETAIL) {
            val place = viewModel.selectedPlace.value
            if (place != null) {
                DetailScreen(
                    place = place,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(route = Routes.MAP) {
            val context = androidx.compose.ui.platform.LocalContext.current
            val place = viewModel.selectedPlace.value
            if (place != null) {
                MapScreen(
                    context = context,
                    place = place
                )
            }
        }

    } }