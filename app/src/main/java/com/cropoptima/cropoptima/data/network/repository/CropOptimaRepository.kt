package com.cropoptima.cropoptima.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.cropoptima.cropoptima.data.network.config.ApiService
import com.cropoptima.cropoptima.data.network.response.HistoryResponse
import com.cropoptima.cropoptima.data.network.response.PredictResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException
import com.cropoptima.cropoptima.utils.Result

class CropOptimaRepository(private val apiService: ApiService){
    fun postHistory(name: String, email: String, password: String): LiveData<Result<HistoryResponse>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postHistory(name,email,password)
            emit(Result.Success(responseMessage))
        } catch (e: SocketTimeoutException){
            val errorMessage = "Koneksi ke server Gagal"
            emit(Result.Error(errorMessage))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, HistoryResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }

    fun postPredict(n: Float,p:Float,k: Float,ph: Float,lat: Float,lon: Float): LiveData<Result<PredictResponse>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postPredict(n,p,k,ph,lat,lon)
            emit(Result.Success(responseMessage))
        } catch (e: SocketTimeoutException){
            val errorMessage = "Koneksi ke server Gagal"
            emit(Result.Error(errorMessage))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, PredictResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }
}