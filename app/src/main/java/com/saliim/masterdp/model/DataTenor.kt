package com.saliim.masterdp.model

import com.google.gson.annotations.SerializedName

class DataTenor{

	@field:SerializedName("create_by")
	val createBy: String? = null

	@field:SerializedName("motor_id")
	val motorId: String? = null

	@field:SerializedName("tenor")
	val tenor: String = ""

	@field:SerializedName("jumlah_dp")
	val jumlahDp: String? = null

	@field:SerializedName("kode")
	val kode: String? = null

	@field:SerializedName("message")
	val message: String? = null

	@field:SerializedName("status")
	val status: String? = null

	var selected: Boolean = false

	override fun toString(): String {
		return tenor
	}

}
