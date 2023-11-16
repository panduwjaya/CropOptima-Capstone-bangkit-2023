package com.cropoptima.cropoptima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    #test commit 1
    fun test (long3: Int): Int {
        return long3
    }

    fun test2 (long2: Int): Int{
        return long2
    }
}
