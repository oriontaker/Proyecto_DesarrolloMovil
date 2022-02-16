package com.aramirez.trabajo_grupal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.OnMapReadyCallback

class maps : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file as the content view.
        setContentView(R.layout.activity_maps)

        // Get a handle to the fragment and register the callback.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    // Get a handle to the GoogleMap object and display marker.
    override fun onMapReady(googleMap: GoogleMap) {


        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(-12.050568, -77.045784))
                .title("Lima")
        )
    }



}

