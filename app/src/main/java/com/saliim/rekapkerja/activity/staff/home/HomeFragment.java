package com.saliim.rekapkerja.activity.staff.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.auth.LoginActivity;
import com.saliim.rekapkerja.adapter.ListKerjaanAdapter;
import com.saliim.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.saliim.rekapkerja.network.ApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public ArrayList<ResponseListKerjaanStaff> data = null;

    public RecyclerView recyclerKerjaanStaff;
    TextView txtLevel, txtHari;

    String harian, hari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        Date d = new Date();
        hari = day.format(d);
        Log.d("day", hari);

        translate();

        recyclerKerjaanStaff = view.findViewById(R.id.rc_kerjaan);
        txtLevel = view.findViewById(R.id.txt_level_kerjaan);
        txtHari = view.findViewById(R.id.txt_hari_kerjaan);

        String level = LoginActivity.levelUser;
        Log.d("levelStaff", level);

        txtLevel.setText(level);
        txtHari.setText(harian);

        getKerjaan(level, hari);
    }

    private void translate() {
        if (hari.equals("Sunday")){
            harian = "Minggu";
        }else if (hari.equals("Monday")){
            harian = "Senin";
        }else if (hari.equals("Tuesday")){
            harian = "Selasa";
        }else if (hari.equals("Wednesday")){
            harian = "Rabu";
        }else if (hari.equals("Thursday")){
            harian = "Kamis";
        }else if (hari.equals("Friday")){
            harian = "Jum'at";
        }else if (hari.equals("Saturday")){
            harian = "Sabtu";
        }
    }

    private void getKerjaan(String level, String hari) {
        ApiClient.service.responseListKerjaan(level, hari).enqueue(new Callback<ArrayList<ResponseListKerjaanStaff>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseListKerjaanStaff>> call, Response<ArrayList<ResponseListKerjaanStaff>> response) {
                if (response.code() == 200){
                    data = response.body();
                    Log.d("listKerjaan", ""+data);

                    if (data == null){
                        Toast.makeText(getActivity(), "Data Null", Toast.LENGTH_SHORT).show();
                    }else{
                        recyclerKerjaanStaff.setHasFixedSize(true);
                        recyclerKerjaanStaff.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerKerjaanStaff.setAdapter(new ListKerjaanAdapter(getActivity(), data));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseListKerjaanStaff>> call, Throwable t) {
//                Toast.makeText(getActivity(), "onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
