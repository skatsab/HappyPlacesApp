package com.example.happyplacesapp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import pub.devrel.easypermissions.EasyPermissions


object LocationUtils {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun hasLocationPermission(context: Context): Boolean {
        return EasyPermissions.hasPermissions(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    @SuppressLint("MissingPermission")
    fun requestCurrentLocation(
        context: Context,
        onLocationResult: (Location?) -> Unit
    ) {
        if (!hasLocationPermission(context)) {
            Toast.makeText(context, "Keine Standort-Berechtigung", Toast.LENGTH_SHORT).show()
            onLocationResult(null)
            return
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location: Location? ->
            onLocationResult(location)
        }.addOnFailureListener {
            Toast.makeText(context, "Standort konnte nicht ermittelt werden", Toast.LENGTH_SHORT).show()
            onLocationResult(null)
        }
    }
}
