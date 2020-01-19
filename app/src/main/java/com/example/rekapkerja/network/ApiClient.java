package com.example.rekapkerja.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.108/idn_rekap_kerja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiInterface service = retrofit.create(ApiInterface.class);
}
