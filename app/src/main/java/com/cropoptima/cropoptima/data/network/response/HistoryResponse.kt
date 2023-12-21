package com.cropoptima.cropoptima.data.network.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("histories")
	val histories: List<HistoriesItem?>? = null,

	@field:SerializedName("error")
	val error: String? = null
)

data class HistoriesItem(

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
