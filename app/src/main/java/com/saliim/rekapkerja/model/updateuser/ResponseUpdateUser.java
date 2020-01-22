package com.saliim.rekapkerja.model.updateuser;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateUser{

	@SerializedName("password_user")
	private String passwordUser;

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

	public void setPasswordUser(String passwordUser){
		this.passwordUser = passwordUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

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

	@Override
 	public String toString(){
		return 
			"ResponseUpdateUser{" + 
			"password_user = '" + passwordUser + '\'' + 
			",kelas_user = '" + kelasUser + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",message = '" + message + '\'' + 
			",username_user = '" + usernameUser + '\'' + 
			",level_user = '" + levelUser + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}