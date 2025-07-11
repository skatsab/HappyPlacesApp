package com.example.happyplacesapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.happyplacesapp.data.HappyPlace
import com.example.happyplacesapp.viewmodel.HappyPlaceViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState

@Composable
fun MainScreen(
    viewModel: HappyPlaceViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToDetail: () -> Unit,
    onNavigateToMap: () -> Unit,
    onItemClick: (HappyPlace) -> Unit
) {
    val places by viewModel.places.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add Place")
            }
        }
    ) { paddingValues ->
        if (places.isEmpty()) {
            // Zentrierte Box, wenn keine Orte vorhanden sind
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Happy Places App",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF8E44AD),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Happy Places App",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF8E44AD),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 16.dp)
                )

                LazyColumn {
                    items(places) { place ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemClick(place)
                                    onNavigateToDetail()
                                }
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = place.imageUri),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = place.title, fontSize = 18.sp)
                                Text(text = place.description, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

