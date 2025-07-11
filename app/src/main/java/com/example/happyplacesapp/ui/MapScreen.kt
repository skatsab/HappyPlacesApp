package com.example.happyplacesapp.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.happyplacesapp.data.HappyPlace
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@SuppressLint("ClickableViewAccessibility")
@Composable
fun MapScreen(context: Context, place: HappyPlace) {
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
    })
}
