package com.example.rekapkerja.network;

import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.example.rekapkerja.model.getlistkerjaan.ResponseListKerjaan;
import com.example.rekapkerja.model.getuser.ResponseGetUsers;
import com.example.rekapkerja.model.login.ResponseLogin;
import com.example.rekapkerja.model.register.ResponseRegister;
import com.example.rekapkerja.model.tambahtask.ResponseTambahTask;

import java.util.ArrayList;

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
    // TODO Adding Employee Task
    @FormUrlEncoded
    @POST("list_kerjaan/create_kerjaan.php")
    Call<ResponseTambahTask> responseTambahTask(@Field("nama_kerjaan") String nama_kerjaan,
                                                @Field("level_kerjaan") String level_kerjaan,
                                                @Field("hari_kerjaan") String hari_kerjaan,
                                                @Field("waktu_kerjaan") String waktu_kerjaan,
                                                @Field("poin_kerjaan") String poin_kerjaan);

    // TODO Read Staff
    @GET("users/get_users.php")
    Call<ArrayList<ResponseGetUsers>> responseGetUsers();

    // TODO Read Staff Task
    @GET("list_kerjaan/get_kerjaan.php")
    Call<ArrayList<ResponseListKerjaan>> responseListKerjaan(@Query("hari_kerjaan") String hari_kerjaan);

}
