package com.cropoptima.cropoptima.main.detection

import androidx.lifecycle.ViewModel
import com.cropoptima.cropoptima.data.network.repository.CropOptimaRepository

class DetectionViewModel(private val repository: CropOptimaRepository): ViewModel() {
    fun postPredict(n: Float,p:Float,k: Float,ph: Float,lat: Float,lon: Float) = repository.postPredict(n,p,k,ph,lat,lon)
}