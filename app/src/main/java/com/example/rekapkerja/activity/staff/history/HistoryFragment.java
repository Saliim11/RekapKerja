package com.example.rekapkerja.activity.staff.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.auth.LoginActivity;
import com.example.rekapkerja.adapter.ListSelesaiAdapter;
import com.example.rekapkerja.model.kerjaanSelesai.ResponseListSelesai;
import com.example.rekapkerja.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    private ArrayList<ResponseListSelesai> data = null;

    private ArrayList<String> poin;

    RecyclerView recyclerSelesai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerSelesai = view.findViewById(R.id.rc_list_selesai);
        String id = LoginActivity.iduser;

        getSelesai(id);

    }

    private void getSelesai(String id) {
        ApiClient.service.responseListSelesai(id).enqueue(new Callback<ArrayList<ResponseListSelesai>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseListSelesai>> call, Response<ArrayList<ResponseListSelesai>> response) {
                if (response.code() == 200){
                    data = response.body();

                    if (data == null){
                        Toast.makeText(getActivity(), "data null", Toast.LENGTH_SHORT).show();
                    }else{
                        recyclerSelesai.setHasFixedSize(true);
                        recyclerSelesai.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerSelesai.setAdapter(new ListSelesaiAdapter(getActivity(), data));

                        poin.add(data.get(0).getPoinSelesai());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseListSelesai>> call, Throwable t) {

            }
        });
    }
}
