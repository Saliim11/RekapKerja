package com.example.rekapkerja.network;

import com.example.rekapkerja.model.kerjaanSelesai.ResponseTambahSelesai;
import com.example.rekapkerja.model.deletekerjaan.ResponseDeleteKerjaan;
import com.example.rekapkerja.model.deleteuser.ResponseDeleteUser;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaan;
import com.example.rekapkerja.model.getuser.ResponseGetUsers;
import com.example.rekapkerja.model.login.ResponseLogin;
import com.example.rekapkerja.model.register.ResponseRegister;
import com.example.rekapkerja.model.listKerjaan.ResponseTambahKerjaan;
import com.example.rekapkerja.model.updatekerjaan.ResponseUpdateKerjaan;
import com.example.rekapkerja.model.updateuser.ResponseUpdateUser;

import java.util.ArrayList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    // TODO Tambah Kerjaan
    @FormUrlEncoded
    @POST("list_kerjaan/create_kerjaan.php")
    Call<ResponseTambahKerjaan> responseTambahKerjaan(@Field("nama_kerjaan") String nama_kerjaan,
                                                      @Field("level_kerjaan") String level_kerjaan,
                                                      @Field("hari_kerjaan") String hari_kerjaan,
                                                      @Field("waktu_kerjaan") String waktu_kerjaan,
                                                      @Field("poin_kerjaan") String poin_kerjaan);

    // TODO Tambah Selesai
    @FormUrlEncoded
    @POST("kerjaan_selesai/create_selesai.php")
    Call<ResponseTambahSelesai> responseTambahSelesai(@Field("id_user") String id_user,
                                                      @Field("kerjaan_selesai") String kerjaan_selesai,
                                                      @Field("poin_selesai") String poin_selesai);

    // TODO Get List Kerjaan by Level
    @GET("list_kerjaan/get_kerjaan_by_level.php")
    Call<ArrayList<ResponseListKerjaanStaff>> responseListKerjaan(@Query("level_kerjaan") String level_kerjaan,
                                                                  @Query("hari_kerjaan") String hari_kerjaan);

    // TODO Get Staff
    @GET("users/get_users.php")
    Call<ArrayList<ResponseGetUsers>> responseGetUsers();

    // TODO Get Staff Task

    // TODO Read Staff Kerjaan
    @GET("list_kerjaan/get_kerjaan.php")
    Call<ArrayList<ResponseListKerjaan>> responseListKerjaan(@Query("hari_kerjaan") String hari_kerjaan);

    // TODO Update Kerjaan
    @PUT("list_kerjaan/update_kerjaan.php")
    Call<ResponseUpdateKerjaan> responseUpdateKerjaan(@Query("id_kerjaan") String id_kerjaan,
                                                      @Query("nama_kerjaan") String nama_kerjaan,
                                                      @Query("level_kerjaan") String level_kerjaan,
                                                      @Query("hari_kerjaan") String hari_kerjaan,
                                                      @Query("waktu_kerjaan") String waktu_kerjaan,
                                                      @Query("poin_kerjaan") String poin_kerjaan);

    // TODO Delete Kerjaan
    @DELETE("list_kerjaan/delete_kerjaan.php")
    Call<ResponseDeleteKerjaan> responseDeleteKerjaan(@Query("id_kerjaan") String id_kerjaan);

    // TODO Update User
    @PUT("users/update_users.php")
    Call<ResponseUpdateUser> responseUpdateUser(@Query("id_user") String id_user,
                                                @Query("nama_user") String nama_user,
                                                @Query("username_user") String username_user,
                                                @Query("password_user") String password_user,
                                                @Query("level_user") String level_user,
                                                @Query("kelas_user") String kelas_user);

    // TODO Delete User
    @DELETE("users/delete_users.php")
    Call<ResponseDeleteUser> responseDeleteUser(@Query("id_user") String id_user);
}
