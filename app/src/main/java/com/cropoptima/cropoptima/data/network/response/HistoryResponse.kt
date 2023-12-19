package com.cropoptima.cropoptima.data.network.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("HistoryResponse")
	val historyResponse: List<HistoryResponseItem?>? = null
)

data class HistoryResponseItem(

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("crop")
	val crop: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

