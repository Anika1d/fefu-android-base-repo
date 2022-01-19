package com.example.treker_fefu.mapscreens.activityscreens

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.treker_fefu.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.treker_fefu.databinding.ActivityMapsBinding
import com.example.treker_fefu.mapscreens.fragmentscreens.StartTrackingFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvTracking.setBackgroundResource(R.drawable.half_round)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.myToolbar.title = "будет прозрачным"
        binding.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.myToolbar.setNavigationOnClickListener {
            finish()
        }

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().apply {
                replace(
                    R.id.containerTracking,
                    StartTrackingFragment(),
                    "startTracking"
                )
                commit()
            }
        else {
            supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.containerTracking,
                    StartTrackingFragment(),
                    "startTracking"
                )
                commit()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in vladik and move the camera
        val sydney = LatLng(43.1056200, 131.8735300)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Vladivostok"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}