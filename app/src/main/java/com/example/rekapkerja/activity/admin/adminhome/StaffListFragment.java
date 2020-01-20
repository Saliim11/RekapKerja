package com.example.rekapkerja.activity.admin.adminhome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rekapkerja.R;
import com.example.rekapkerja.adapter.ListStaffAdapter;
import com.example.rekapkerja.model.getuser.ResponseGetUsers;
import com.example.rekapkerja.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffListFragment extends Fragment {

    ArrayList<ResponseGetUsers> data;
    RecyclerView recyclerStaff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.staff_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerStaff = view.findViewById(R.id.recyclerStaff);

        getReadStaff();
    }

    private void getReadStaff() {
        ApiClient.service.responseGetUsers().enqueue(new Callback<ArrayList<ResponseGetUsers>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetUsers>> call, Response<ArrayList<ResponseGetUsers>> response) {
                if (response.code() == 200) {
                    data = response.body();

                    if (data == null) {
                        Toast.makeText(getActivity(), "Data tidak ada", Toast.LENGTH_SHORT).show();
                    } else {
                        recyclerStaff.setHasFixedSize(true);
                        recyclerStaff.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerStaff.setAdapter(new ListStaffAdapter(getActivity(), data));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetUsers>> call, Throwable t) {

            }
        });
    }
}
