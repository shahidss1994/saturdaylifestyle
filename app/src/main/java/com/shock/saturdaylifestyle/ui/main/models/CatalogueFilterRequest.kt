package com.shock.saturdaylifestyle.ui.main.models

import com.google.gson.annotations.SerializedName

data class CatalogueFilterRequest(

	@field:SerializedName("fit")
	val fit: List<String>? = null,

	@field:SerializedName("is_hto")
	val isHto: Boolean? = null,

	@field:SerializedName("face_shape")
	val faceShape: List<String>? = null,

	@field:SerializedName("material")
	val material: List<String>? = null,

	@field:SerializedName("color")
	val color: List<String>? = null,

	@field:SerializedName("gender")
	val gender: List<String>? = null,

	@field:SerializedName("frame_shape")
	val frameShape: List<String>? = null,

	@field:SerializedName("sort_by")
	val sortBy: SortBy? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("product_category")
	val productCategory: Int? = null
)

data class SortBy(

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("order")
	val order: String? = null
)
