package com.saliim.rekapkerja.activity.admin.adminhome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.adapter.ListKerjaanStaffAdapter;
import com.saliim.rekapkerja.model.listKerjaan.ResponseListKerjaan;
import com.saliim.rekapkerja.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHomeFragment extends Fragment {

    ArrayList<ResponseListKerjaan> data;

    RecyclerView recyclerListKerjaan;
    FloatingActionButton floatingActionButton;
    TextView txtNull;
    Spinner spinFilterHari;
    Button btnSearch;

    String hari;

    String[] harian = {"Ahad", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
    String[] hari_value = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.admin_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerListKerjaan = view.findViewById(R.id.recyclerListKerjaan);
        floatingActionButton = view.findViewById(R.id.floatingButton);
        spinFilterHari = view.findViewById(R.id.spinFilterHari);
        btnSearch = view.findViewById(R.id.btnSearch);
        txtNull = view.findViewById(R.id.txtNull);

        getReadKerjaan("Sunday");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, harian);
        spinFilterHari.setAdapter(adapter);

        spinFilterHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String isi_hari = hari_value[position];
                hari = isi_hari;
                Log.d("isihari", hari);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TambahTaskActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReadKerjaan(hari);
            }
        });
    }

    private void getReadKerjaan(String hari) {
        ApiClient.service.responseListKerjaan(hari).enqueue(new Callback<ArrayList<ResponseListKerjaan>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ArrayList<ResponseListKerjaan>> call, Response<ArrayList<ResponseListKerjaan>> response) {
                if (response.code() == 200) {
                    data = response.body();

                    if (data.isEmpty()) {

                        String harihari = spinFilterHari.getSelectedItem().toString();

                        txtNull.setVisibility(View.VISIBLE);
                        recyclerListKerjaan.setVisibility(View.GONE);
                        txtNull.setText("Data Hari "+harihari+" tidak ada");
                    } else {
                        recyclerListKerjaan.setHasFixedSize(true);
                        recyclerListKerjaan.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerListKerjaan.setAdapter(new ListKerjaanStaffAdapter(getActivity(), data));
                        txtNull.setVisibility(View.GONE);
                        recyclerListKerjaan.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseListKerjaan>> call, Throwable t) {

            }
        });
    }
}
