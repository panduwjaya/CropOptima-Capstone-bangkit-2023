package com.cropoptima.cropoptima.data.network.config

import com.cropoptima.cropoptima.data.network.response.HistoryResponse
import com.cropoptima.cropoptima.data.network.response.PredictResponse
import retrofit2.http.POST

interface ApiService {
    @POST("history?")
    suspend fun postHistory(

    ): HistoryResponse

    @POST("register")
    suspend fun postPredict(

    ): PredictResponse
}