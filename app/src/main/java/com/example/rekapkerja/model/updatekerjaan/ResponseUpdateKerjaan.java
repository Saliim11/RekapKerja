package com.example.rekapkerja.model.updatekerjaan;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateKerjaan{

	@SerializedName("poin_kerjaan")
	private String poinKerjaan;

	@SerializedName("waktu_kerjaan")
	private String waktuKerjaan;

	@SerializedName("nama_kerjaan")
	private String namaKerjaan;

	@SerializedName("hari_kerjaan")
	private String hariKerjaan;

	@SerializedName("level_kerjaan")
	private String levelKerjaan;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setPoinKerjaan(String poinKerjaan){
		this.poinKerjaan = poinKerjaan;
	}

	public String getPoinKerjaan(){
		return poinKerjaan;
	}

	public void setWaktuKerjaan(String waktuKerjaan){
		this.waktuKerjaan = waktuKerjaan;
	}

	public String getWaktuKerjaan(){
		return waktuKerjaan;
	}

	public void setNamaKerjaan(String namaKerjaan){
		this.namaKerjaan = namaKerjaan;
	}

	public String getNamaKerjaan(){
		return namaKerjaan;
	}

	public void setHariKerjaan(String hariKerjaan){
		this.hariKerjaan = hariKerjaan;
	}

	public String getHariKerjaan(){
		return hariKerjaan;
	}

	public void setLevelKerjaan(String levelKerjaan){
		this.levelKerjaan = levelKerjaan;
	}

	public String getLevelKerjaan(){
		return levelKerjaan;
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