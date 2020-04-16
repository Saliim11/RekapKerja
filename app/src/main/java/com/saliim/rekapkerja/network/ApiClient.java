package com.saliim.rekapkerja.network;

import com.saliim.rekapkerja.utils.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.7/idn_rekap_kerja/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiInterface service = retrofit.create(ApiInterface.class);
}
