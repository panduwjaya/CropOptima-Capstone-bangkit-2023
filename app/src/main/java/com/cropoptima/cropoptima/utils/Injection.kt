package com.cropoptima.cropoptima.utils

import android.content.Context
import com.cropoptima.cropoptima.data.network.config.ApiConfig
import com.cropoptima.cropoptima.data.network.repository.CropOptimaRepository
import com.cropoptima.cropoptima.main.setting.SettingsPreference
import com.cropoptima.cropoptima.utils.Utils.getCurrentUserIdToken

object Injection {
    fun provideRepository(context: Context): CropOptimaRepository {
        val apiService = ApiConfig.getApiService()
        return CropOptimaRepository.getInstance(apiService)
    }

}