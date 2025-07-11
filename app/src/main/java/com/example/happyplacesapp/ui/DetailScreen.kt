package com.example.happyplacesapp.ui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.happyplacesapp.data.HappyPlace
import com.example.happyplacesapp.viewmodel.HappyPlaceViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("ClickableViewAccessibility")
@Composable
fun DetailScreen(
    place: HappyPlace,
    viewModel: HappyPlaceViewModel,
    onBack: () -> Unit
) {
    var notes by remember { mutableStateOf(place.notes) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 📸 Bild anzeigen (falls vorhanden)
        if (place.imageUri.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(Uri.parse(place.imageUri)),
                contentDescription = "Bild von ${place.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(text = place.title, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = place.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notizen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🗺️ OSMDroid-Karte
        AndroidView(factory = {
            Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(place.latitude, place.longitude))

                val marker = Marker(this)
                marker.position = GeoPoint(place.latitude, place.longitude)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = place.title
                overlays.add(marker)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(250.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val updatedPlace = place.copy(notes = notes)
                viewModel.updatePlace(updatedPlace)

                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Änderungen speichern")
        }
    }
}
