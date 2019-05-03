package com.saliim.masterdp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDp {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("kode")
	@Expose
	private String kode;
	@SerializedName("motor_id")
	@Expose
	private String motorId;
	@SerializedName("create_by")
	@Expose
	private String createBy;
	@SerializedName("jumlah_dp")
	@Expose
	private String jumlahDp;
	@SerializedName("tenor")
	@Expose
	private String tenor;
	@SerializedName("created")
	@Expose
	private String created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getMotorId() {
		return motorId;
	}

	public void setMotorId(String motorId) {
		this.motorId = motorId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getJumlahDp() {
		return jumlahDp;
	}

	public void setJumlahDp(String jumlahDp) {
		this.jumlahDp = jumlahDp;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}