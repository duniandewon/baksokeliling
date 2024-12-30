package com.example.home.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Looper
import com.example.home.domain.model.UserLocation
import com.example.home.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : LocationRepository {
    override fun getLocationUpdates(): Flow<UserLocation> = callbackFlow {
        val request = LocationRequest.Builder(10000L).setMinUpdateIntervalMillis(5000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY).build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let {
                    trySend(UserLocation(it.latitude, it.longitude))
                }
            }

            override fun onLocationAvailability(availability: LocationAvailability) {
                if (!availability.isLocationAvailable) {
                    close(Throwable("Location not available"))
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    override fun getCurrentLocation(): Flow<UserLocation> = callbackFlow {
        val locationTask = fusedLocationProviderClient.lastLocation

        locationTask.addOnSuccessListener { location ->
            if (location != null) {
                this@callbackFlow.trySend(UserLocation(location.latitude, location.longitude))
            } else {
                close(Throwable("Location is null"))
            }
        }.addOnFailureListener { exception ->
            close(exception)
        }

        awaitClose()
    }
}