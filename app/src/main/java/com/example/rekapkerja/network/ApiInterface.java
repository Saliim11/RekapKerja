package com.example.rekapkerja.network;

import com.example.rekapkerja.model.login.ResponseLogin;
import com.example.rekapkerja.model.register.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    // TODO Login
    @FormUrlEncoded
    @POST("users/login.php")
    Call<ResponseLogin> responseLogin(@Field("username_user") String username_user,
                                      @Field("password_user") String password_user);

    // TODO Register
    @FormUrlEncoded
    @POST("users/register.php")
    Call<ResponseRegister> responseRegister(@Field("nama_user") String nama_user,
                                            @Field("username_user") String username_user,
                                            @Field("password_user") String password_user,
                                            @Field("level_user") String level_user,
                                            @Field("kelas_user") String kelas_user);

}
