package com.saliim.masterdp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertDataDp{

	@SerializedName("create_by")
	@Expose
	private String createBy;

	@SerializedName("motor_id")
	@Expose
	private String motorId;

	@SerializedName("tenor")
	@Expose
	private int tenor;

	@SerializedName("jumlah_dp")
	@Expose
	private String jumlahDp;

	@SerializedName("kode")
	@Expose
	private String kode;

	@SerializedName("message")
	@Expose
	private String message;

	@SerializedName("status")
	@Expose
	private String status;

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setMotorId(String motorId){
		this.motorId = motorId;
	}

	public String getMotorId(){
		return motorId;
	}

	public void setTenor(int tenor){
		this.tenor = tenor;
	}

	public int getTenor(){
		return tenor;
	}

	public void setJumlahDp(String jumlahDp){
		this.jumlahDp = jumlahDp;
	}

	public String getJumlahDp(){
		return jumlahDp;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}