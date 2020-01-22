package com.saliim.rekapkerja.model.kerjaanSelesai;

import com.google.gson.annotations.SerializedName;

public class ResponseListSelesai{

	@SerializedName("poin_selesai")
	private String poinSelesai;

	@SerializedName("tgl_selesai")
	private String tglSelesai;

	@SerializedName("kerjaan_selesai")
	private String kerjaanSelesai;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_selesai")
	private String idSelesai;

	public void setPoinSelesai(String poinSelesai){
		this.poinSelesai = poinSelesai;
	}

	public String getPoinSelesai(){
		return poinSelesai;
	}

	public void setTglSelesai(String tglSelesai){
		this.tglSelesai = tglSelesai;
	}

	public String getTglSelesai(){
		return tglSelesai;
	}

	public void setKerjaanSelesai(String kerjaanSelesai){
		this.kerjaanSelesai = kerjaanSelesai;
	}

	public String getKerjaanSelesai(){
		return kerjaanSelesai;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setIdSelesai(String idSelesai){
		this.idSelesai = idSelesai;
	}

	public String getIdSelesai(){
		return idSelesai;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListSelesai{" + 
			"poin_selesai = '" + poinSelesai + '\'' + 
			",tgl_selesai = '" + tglSelesai + '\'' + 
			",kerjaan_selesai = '" + kerjaanSelesai + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id_selesai = '" + idSelesai + '\'' + 
			"}";
		}
}