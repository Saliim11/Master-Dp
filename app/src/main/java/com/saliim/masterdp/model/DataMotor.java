package com.saliim.masterdp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMotor{

	@SerializedName("batas_diskon")
	@Expose
	private String batasDiskon;

	@SerializedName("created")
	@Expose
	private String created;

	@SerializedName("motor_categoty_id")
	@Expose
	private String motorCategotyId;

	@SerializedName("batas_diskon_tunai")
	@Expose
	private String batasDiskonTunai;

	@SerializedName("thn_motor")
	@Expose
	private String thnMotor;

	@SerializedName("modi_by")
	@Expose
	private String modiBy;

	@SerializedName("create_by")
	@Expose
	private String createBy;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("modified")
	@Expose
	private String modified;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("harga_jual")
	@Expose
	private String hargaJual;

	@SerializedName("ind")
	@Expose
	private String ind;

	@SerializedName("no_ind")
	@Expose
	private String noInd;

	public void setBatasDiskon(String batasDiskon){
		this.batasDiskon = batasDiskon;
	}

	public String getBatasDiskon(){
		return batasDiskon;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setMotorCategotyId(String motorCategotyId){
		this.motorCategotyId = motorCategotyId;
	}

	public String getMotorCategotyId(){
		return motorCategotyId;
	}

	public void setBatasDiskonTunai(String batasDiskonTunai){
		this.batasDiskonTunai = batasDiskonTunai;
	}

	public String getBatasDiskonTunai(){
		return batasDiskonTunai;
	}

	public void setThnMotor(String thnMotor){
		this.thnMotor = thnMotor;
	}

	public String getThnMotor(){
		return thnMotor;
	}

	public void setModiBy(String modiBy){
		this.modiBy = modiBy;
	}

	public String getModiBy(){
		return modiBy;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHargaJual(String hargaJual){
		this.hargaJual = hargaJual;
	}

	public String getHargaJual(){
		return hargaJual;
	}

	public void setInd(String ind){
		this.ind = ind;
	}

	public String getInd(){
		return ind;
	}

	public void setNoInd(String noInd){
		this.noInd = noInd;
	}

	public String getNoInd(){
		return noInd;
	}

	@Override
	public String toString(){
		return motorCategotyId;
	}
}