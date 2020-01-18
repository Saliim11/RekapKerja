package com.example.rekapkerja.activity.staff.home;

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

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.auth.LoginActivity;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.example.rekapkerja.network.ApiClient;

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
        String hari = day.format(d);
        Log.d("day", hari);

        recyclerKerjaanStaff = view.findViewById(R.id.rc_kerjaan);
        txtLevel = view.findViewById(R.id.txt_level_kerjaan);
        txtHari = view.findViewById(R.id.txt_hari_kerjaan);

        String level = LoginActivity.leveluser;
        Log.d("levelStaff", level);

        txtLevel.setText(level);
        txtHari.setText(hari);

        getKerjaan(level, hari);
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
                Toast.makeText(getActivity(), "onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
