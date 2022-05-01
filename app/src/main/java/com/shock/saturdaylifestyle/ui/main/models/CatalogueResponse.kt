package com.shock.saturdaylifestyle.ui.main.models

import com.google.gson.annotations.SerializedName
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreOurTopPicksViewState
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreTopPicksItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.NewArrivalItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.WhatTheySayItemViewState

data class CatalogueResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class VariantsItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("variant_code")
	val variantCode: String? = null,

	@field:SerializedName("prescription_image_key")
	val prescriptionImageKey: String? = null,

	@field:SerializedName("retail_price")
	val retailPrice: Int? = null,

	@field:SerializedName("currency_code")
	val currencyCode: String? = null,

	@field:SerializedName("tax_rate")
	val taxRate: Int? = null,

	@field:SerializedName("is_wishlisted")
	val isWishlisted: Boolean? = null,

	@field:SerializedName("variant_id")
	val variantId: String? = null,

	@field:SerializedName("sizeVariants")
	val sizeVariants: List<SizeVariantsItem?>? = null,

	@field:SerializedName("size_label")
	val sizeLabel: String? = null,

	@field:SerializedName("last_modified")
	val lastModified: String? = null,

	@field:SerializedName("is_hto")
	val isHto: Boolean? = null,

	@field:SerializedName("top_pick")
	val topPick: Boolean? = null,

	@field:SerializedName("size_key")
	val sizeKey: String? = null,

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("frame_default_image_key")
	val frameDefaultImageKey: String? = null,

	@field:SerializedName("turboly_id")
	val turbolyId: String? = null,

	@field:SerializedName("size_detail")
	val sizeDetail: SizeDetail? = null,

	@field:SerializedName("icon_image_key")
	val iconImageKey: String? = null,

	@field:SerializedName("base_url")
	val baseUrl: String? = null,

	@field:SerializedName("variant_name")
	val variantName: String? = null,

	@field:SerializedName("is_hto_added")
	val isHtoAdded: Boolean? = null,

	@field:SerializedName("is_sunwear")
	val isSunwear: Boolean? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("is_vto")
	val isVto: Boolean? = null,

	@field:SerializedName("sku_code")
	val skuCode: String? = null,

	@field:SerializedName("material_image_key")
	val materialImageKey: String? = null,

	@field:SerializedName("product_category")
	val productCategory: Int? = null,

	@field:SerializedName("size_code")
	val sizeCode: String? = null
)

data class FaceShapeDetailsItem(

	@field:SerializedName("female_icon_url")
	val femaleIconUrl: String? = null,

	@field:SerializedName("male_icon_url")
	val maleIconUrl: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class Data(

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class FrameShapeDetailsItem(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class MaterialDetails(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ImagesItem(

	@field:SerializedName("image_category")
	val imageCategory: String? = null,

	@field:SerializedName("image_key")
	val imageKey: String? = null,

	@field:SerializedName("frame_code")
	val frameCode: String? = null,

	@field:SerializedName("image_code")
	val imageCode: String? = null,

	@field:SerializedName("sku_code")
	val skuCode: String? = null,

	@field:SerializedName("variant_code")
	val variantCode: String? = null,

	@field:SerializedName("image_type")
	val imageType: String? = null
)

data class SizeDetail(

	@field:SerializedName("size_key")
	val sizeKey: String? = null,

	@field:SerializedName("temple_length")
	val templeLength: String? = null,

	@field:SerializedName("size_label")
	val sizeLabel: String? = null,

	@field:SerializedName("bridge")
	val bridge: String? = null,

	@field:SerializedName("lense_width")
	val lenseWidth: String? = null,

	@field:SerializedName("front_width")
	val frontWidth: String? = null
)

data class SizeVariantsItem(

	@field:SerializedName("is_hto")
	val isHto: Boolean? = null,

	@field:SerializedName("size_key")
	val sizeKey: String? = null,

	@field:SerializedName("turboly_id")
	val turbolyId: String? = null,

	@field:SerializedName("is_hto_added")
	val isHtoAdded: Boolean? = null,

	@field:SerializedName("retail_price")
	val retailPrice: Int? = null,

	@field:SerializedName("currency_code")
	val currencyCode: String? = null,

	@field:SerializedName("is_wishlisted")
	val isWishlisted: Boolean? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("temple_length")
	val templeLength: String? = null,

	@field:SerializedName("is_vto")
	val isVto: Boolean? = null,

	@field:SerializedName("size_label")
	val sizeLabel: String? = null,

	@field:SerializedName("bridge")
	val bridge: String? = null,

	@field:SerializedName("sku_code")
	val skuCode: String? = null,

	@field:SerializedName("lense_width")
	val lenseWidth: String? = null,

	@field:SerializedName("front_width")
	val frontWidth: String? = null,

	@field:SerializedName("size_code")
	val sizeCode: String? = null
)

data class ProductsItem(

	@field:SerializedName("frame_name")
	val frameName: String? = null,

	@field:SerializedName("frame_description")
	val frameDescription: String? = null,

	@field:SerializedName("face_shape")
	val faceShape: List<String?>? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("frame_code")
	val frameCode: String? = null,

	@field:SerializedName("base_url")
	val baseUrl: String? = null,

	@field:SerializedName("variants")
	val variants: List<VariantsItem?>? = null,

	@field:SerializedName("fit")
	val fit: String? = null,

	@field:SerializedName("material")
	val material: String? = null,

	@field:SerializedName("frame_shape")
	val frameShape: List<String?>? = null,

	@field:SerializedName("faceShapeDetails")
	val faceShapeDetails: List<FaceShapeDetailsItem?>? = null,

	@field:SerializedName("frameShapeDetails")
	val frameShapeDetails: List<FrameShapeDetailsItem?>? = null,

	@field:SerializedName("materialDetails")
	val materialDetails: MaterialDetails? = null,

	@field:SerializedName("frame_id")
	val frameId: String? = null
){

	fun toNewArrivalItemViewState(): NewArrivalItemViewState {
		val newArrivalItemViewState = NewArrivalItemViewState()
		newArrivalItemViewState.id = this.frameId
		newArrivalItemViewState.title = this.frameName ?: ""
		return newArrivalItemViewState
	}


	fun toExploreTopPicksItemViewState(): ExploreTopPicksItemViewState {
		val newExploreTopPicksItemViewState = ExploreTopPicksItemViewState()
		newExploreTopPicksItemViewState.id = this.frameId
		newExploreTopPicksItemViewState.title = this.frameName ?: ""
		return newExploreTopPicksItemViewState
	}

	fun toWhatTheySayItemViewState(): WhatTheySayItemViewState {
		val newExploreTopPicksItemViewState = WhatTheySayItemViewState()
		newExploreTopPicksItemViewState.id = this.frameId
		newExploreTopPicksItemViewState.title = this.frameName ?: ""
		return newExploreTopPicksItemViewState
	}

}
