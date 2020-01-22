package com.saliim.rekapkerja.model.login;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("kelas_user")
	private String kelasUser;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("username_user")
	private String usernameUser;

	@SerializedName("level_user")
	private String levelUser;

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

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
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

	@Override
 	public String toString(){
		return 
			"User{" + 
			"password_user = '" + passwordUser + '\'' + 
			",kelas_user = '" + kelasUser + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",username_user = '" + usernameUser + '\'' + 
			",level_user = '" + levelUser + '\'' + 
			"}";
		}
}