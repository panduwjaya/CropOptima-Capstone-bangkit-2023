package com.cropoptima.cropoptima.data.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.cropoptima.cropoptima.data.network.config.ApiService
import com.cropoptima.cropoptima.data.network.response.HistoryResponse
import com.cropoptima.cropoptima.data.network.response.PredictResponse
import com.cropoptima.cropoptima.utils.Result
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException

class CropOptimaRepository(
    private val apiService: ApiService,
){

    companion object {
        @Volatile
        private var instance: CropOptimaRepository? = null
        fun getInstance(
            apiService: ApiService
        ): CropOptimaRepository =
            instance ?: synchronized(this) {
                instance ?: CropOptimaRepository(apiService)
            }.also { instance = it }
    }
    fun postHistory(idToken: String): LiveData<Result<HistoryResponse>> = liveData{
        Log.i("info", idToken)

        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postHistory(idToken)
            Log.i("info", responseMessage.error.toString())
            emit(Result.Success(responseMessage))
        } catch (e: SocketTimeoutException){
            val errorMessage = "Koneksi ke server Gagal"
            Log.i("info", errorMessage)

            emit(Result.Error(errorMessage))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, HistoryResponse::class.java)
            val errorMessage = errorBody.error
            Log.i("info", e.message())
            emit(Result.Error(errorMessage ?: "error"))
        }
    }

    fun postPredict(idToken: String,n: Float,p:Float,k: Float,ph: Float,lat: Float,lon: Float): LiveData<Result<PredictResponse>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postPredict(idToken,n,p,k,ph,lat,lon)
            emit(Result.Success(responseMessage))
        } catch (e: SocketTimeoutException){
            val errorMessage = "Koneksi ke server Gagal"
            emit(Result.Error(errorMessage))
        } catch (e: HttpException) {
            //get error message
            Log.i("info", e.message().toString())

            emit(Result.Error(e.message().toString() ?: "error"))
        }
    }
}