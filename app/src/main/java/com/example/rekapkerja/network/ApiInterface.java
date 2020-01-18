package com.example.rekapkerja.network;

import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.example.rekapkerja.model.login.ResponseLogin;
import com.example.rekapkerja.model.register.ResponseRegister;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    // TODO Get List Kerjaan by Level
    @GET("list_kerjaan/get_kerjaan_by_level.php")
    Call<ArrayList<ResponseListKerjaanStaff>> responseListKerjaan(@Query("level_kerjaan") String level_kerjaan,
                                                                  @Query("hari_kerjaan") String hari_kerjaan);

}
