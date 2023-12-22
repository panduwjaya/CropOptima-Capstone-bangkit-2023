package com.cropoptima.cropoptima.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cropoptima.cropoptima.data.network.repository.CropOptimaRepository
import com.cropoptima.cropoptima.main.detection.DetectionViewModel
import com.cropoptima.cropoptima.main.home.HomeViewModel
import com.cropoptima.cropoptima.utils.Injection.provideRepository

class MainViewModelFactory private constructor(private val cropOptimaRepository: CropOptimaRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetectionViewModel::class.java)){
            return DetectionViewModel(cropOptimaRepository) as T
        }   else     if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(cropOptimaRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null
        fun getInstance(context: Context): MainViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(
                    provideRepository(context)
                )
            }.also { instance = it }
    }
}