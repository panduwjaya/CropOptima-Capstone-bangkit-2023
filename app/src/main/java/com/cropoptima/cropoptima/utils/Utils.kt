package com.cropoptima.cropoptima.utils

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
            Log.i("info", it.result.token.toString())
        }
        return token
    }
}