package com.cropoptima.cropoptima.data.network.config

import com.cropoptima.cropoptima.data.network.response.HistoryResponse
import com.cropoptima.cropoptima.data.network.response.PredictResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("history")
    suspend fun postHistory(
        @Field("idToken") idToken: String,
    ): HistoryResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun postPredict(
        @Field("idToken") idToken: String,
        @Field("n") n: Float,
        @Field("p") p: Float,
        @Field("k") k: Float,
        @Field("ph") ph: Float,
        @Field("lat") lat: Float,
        @Field("lon") lon: Float,
    ): PredictResponse
}