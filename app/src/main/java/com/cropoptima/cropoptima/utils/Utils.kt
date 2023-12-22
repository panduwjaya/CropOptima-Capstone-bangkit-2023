package com.cropoptima.cropoptima.utils

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

object Utils {

    fun getCurrentUserIdToken(): String {
        val auth: FirebaseAuth = Firebase.auth
        val user: FirebaseUser = auth.currentUser!!
        var token = ""
        user.getIdToken(true).addOnCompleteListener {
            token = it.result.token.toString()
        }
        return token
    }

    fun parseAddressLocation(
        context: Context,
        lat: Double,
        lon: Double
    ): String {
        val geocoder = Geocoder(context)
        val geoLocation = geocoder.getFromLocation(lat, lon, 1)
        return if (geoLocation?.size!! > 0) {
            val location = geoLocation?.get(0)
            val fullAddress = location?.getAddressLine(0)
            StringBuilder("📌 ")
                .append(fullAddress).toString()
        } else {
            "📌 Location Unknown"
        }
    }
}