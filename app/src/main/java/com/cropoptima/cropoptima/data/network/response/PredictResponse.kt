package com.cropoptima.cropoptima.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class PredictResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: Message? = null
)

@Parcelize
data class Message(

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("crop")
	val crop: String? = null
): Parcelable
