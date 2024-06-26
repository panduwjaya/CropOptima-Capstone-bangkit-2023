package com.cropoptima.cropoptima.main.maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cropoptima.cropoptima.R
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.cropoptima.cropoptima.databinding.CustomMapsBinding
import com.cropoptima.cropoptima.main.setting.SettingsPreference
import com.cropoptima.cropoptima.main.setting.SettingsViewModel
import com.cropoptima.cropoptima.main.setting.SettingsViewModelFactory
import com.cropoptima.cropoptima.utils.Utils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.MapStyleOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: CustomMapsBinding
    private val indonesiaLocation = LatLng(-2.3932797, 108.8507139)

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingsPreference.getInstance(application.dataStore)
        val settingViewModel = ViewModelProvider(this, SettingsViewModelFactory(pref)).get(
            SettingsViewModel::class.java
        )

        binding = CustomMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isTiltGesturesEnabled = true
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesiaLocation, 4f))
        mMap.setOnInfoWindowClickListener { marker ->
            postLocationSelected(marker.position.latitude, marker.position.longitude)
            marker.hideInfoWindow()
        }
        mMap.setOnMapClickListener {
            mMap.clear()
            mMap.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            it.latitude,
                            it.longitude
                        )
                    )
            )?.showInfoWindow()
        }
        mMap.setOnPoiClickListener {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            mMap.clear()
            mMap.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            it.latLng.latitude,
                            it.latLng.longitude
                        )
                    )
            )?.showInfoWindow()
        }
        setMapStyle()
        getMyLastLocation()
    }

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                /* if user location fetched -> add marker & trigger input to user location */
                location?.let {
                    mMap.addMarker(
                        MarkerOptions().position(LatLng(it.latitude, it.longitude))
                    )
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 20f)
                    )
                    postLocationSelected(it.latitude, it.longitude)
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun postLocationSelected(lat: Double, lon: Double) {
        val address =
            Utils.parseAddressLocation(
                this,
                lat,
                lon
            )
        binding.addressBar.text = address
//        settingViewModel.saveLatAndLot(lat,lon)

    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                    // Precise location access granted.
                    getMyLastLocation()
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    // Only approximate location access granted.
                    getMyLastLocation()
                }
                else -> {
                    // No location access granted.
                }
            }
        }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setMapStyle() {
        try {
            val success =
                mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this,
                        R.raw.gmaps_style
                    )
                )
            if (!success) {
                Log.e("MAPS", "Style parsing failed.")
            }
        } catch (exception: Resources.NotFoundException) {
            Log.e("MAPS", "Can't find style. Error: ", exception)
        }
    }
}