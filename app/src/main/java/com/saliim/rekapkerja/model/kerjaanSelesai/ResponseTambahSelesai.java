package com.saliim.rekapkerja.model.kerjaanSelesai;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahSelesai{

	@SerializedName("poin_selesai")
	private String poinSelesai;

	@SerializedName("kerjaan_selesai")
	private String kerjaanSelesai;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setPoinSelesai(String poinSelesai){
		this.poinSelesai = poinSelesai;
	}

	public String getPoinSelesai(){
		return poinSelesai;
	}

	public void setKerjaanSelesai(String kerjaanSelesai){
		this.kerjaanSelesai = kerjaanSelesai;
	}

	public String getKerjaanSelesai(){
		return kerjaanSelesai;
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

	@Override
 	public String toString(){
		return 
			"ResponseTambahSelesai{" + 
			"poin_selesai = '" + poinSelesai + '\'' + 
			",kerjaan_selesai = '" + kerjaanSelesai + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}