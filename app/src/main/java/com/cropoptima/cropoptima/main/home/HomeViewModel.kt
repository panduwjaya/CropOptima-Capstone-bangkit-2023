package com.cropoptima.cropoptima.main.home

import androidx.lifecycle.ViewModel
import com.cropoptima.cropoptima.data.network.repository.CropOptimaRepository

class HomeViewModel(private val repository: CropOptimaRepository): ViewModel() {
    fun postHistory(idToken: String) = repository.postHistory(idToken)
}