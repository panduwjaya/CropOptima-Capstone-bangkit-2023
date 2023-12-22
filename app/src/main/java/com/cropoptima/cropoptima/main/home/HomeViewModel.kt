package com.cropoptima.cropoptima.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cropoptima.cropoptima.data.network.repository.CropOptimaRepository
import com.cropoptima.cropoptima.data.network.response.HistoryResponse
import com.cropoptima.cropoptima.utils.Result

class HomeViewModel(private val repository: CropOptimaRepository) : ViewModel() {
    fun postHistory(idToken: String): LiveData<Result<HistoryResponse>> {
        Log.i("info", "called2")
        return repository.postHistory(idToken)
    }
}