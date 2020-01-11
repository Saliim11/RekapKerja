package com.example.rekapkerja.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("kelas_user")
	private String kelasUser;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("message")
	private String message;

	@SerializedName("username_user")
	private String usernameUser;

	@SerializedName("level_user")
	private String levelUser;

	@SerializedName("status")
	private String status;

	public void setKelasUser(String kelasUser){
		this.kelasUser = kelasUser;
	}

	public String getKelasUser(){
		return kelasUser;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUsernameUser(String usernameUser){
		this.usernameUser = usernameUser;
	}

	public String getUsernameUser(){
		return usernameUser;
	}

	public void setLevelUser(String levelUser){
		this.levelUser = levelUser;
	}

	public String getLevelUser(){
		return levelUser;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}