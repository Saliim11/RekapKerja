package com.saliim.rekapkerja.activity.staff.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.auth.LoginActivity;
import com.saliim.rekapkerja.adapter.ListSelesaiAdapter;
import com.saliim.rekapkerja.model.kerjaanSelesai.ResponseListSelesai;
import com.saliim.rekapkerja.network.ApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    private ArrayList<ResponseListSelesai> data = null;

    private String poins;
    private int point;
    private ArrayList<Integer> poin = new ArrayList<>();
    private int sum = 0;

    private RecyclerView recyclerSelesai;
    private TextView txtTotalPoin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtTotalPoin = view.findViewById(R.id.txtTotalPoin);
        recyclerSelesai = view.findViewById(R.id.rc_list_selesai);

        String id = LoginActivity.idUser;
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

                        for (ResponseListSelesai dataSelesai: data){
                            poins = dataSelesai.getPoinSelesai();
//                            Log.d("poinString", poins);

                            point = Integer.parseInt(poins);
                            Log.d("poinInteger", String.valueOf(point));

                            poin.add(point);
                            Log.d("poin2", poin.toString());

                        }
                        for (int i : poin){
                            sum += i;
                        }

                        Log.d("total", String.valueOf(sum));
                        String hasil = String.valueOf(sum);

                        txtTotalPoin.setText(hasil);

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseListSelesai>> call, Throwable t) {

            }
        });
    }
}
