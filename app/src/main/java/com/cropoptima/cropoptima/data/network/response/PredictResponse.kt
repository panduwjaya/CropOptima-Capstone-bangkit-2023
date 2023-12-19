package com.cropoptima.cropoptima.data.network.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("crop")
	val crop: String? = null
)
