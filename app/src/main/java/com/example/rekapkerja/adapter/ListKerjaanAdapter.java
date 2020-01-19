package com.example.rekapkerja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.auth.LoginActivity;
import com.example.rekapkerja.activity.staff.home.HomeFragment;
import com.example.rekapkerja.model.kerjaanSelesai.ResponseTambahSelesai;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;
import com.example.rekapkerja.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKerjaanAdapter extends RecyclerView.Adapter<ListKerjaanAdapter.ViewHolder> {
    Context context;
    List<ResponseListKerjaanStaff> dataKerjaan;

    public ListKerjaanAdapter(Context context, ArrayList<ResponseListKerjaanStaff> dataKerjaan){
        this.context = context;
        this.dataKerjaan = dataKerjaan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kerjaan_staff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String id = LoginActivity.idUser;
        String namaKerjaan = dataKerjaan.get(position).getNamaKerjaan();
        String waktuKerjaan = dataKerjaan.get(position).getWaktuKerjaan();
        String poinKerjaan = dataKerjaan.get(position).getPoinKerjaan();

        holder.txtKerjaan.setText(namaKerjaan);
        holder.txtWaktu.setText(waktuKerjaan);
        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selesaiKerjain(id, namaKerjaan, poinKerjaan);
            }
        });
    }

    private void selesaiKerjain(String id, String namaKerjaan, String poinKerjaan) {
        ApiClient.service.responseTambahSelesai(id, namaKerjaan, poinKerjaan).enqueue(new Callback<ResponseTambahSelesai>() {
            @Override
            public void onResponse(Call<ResponseTambahSelesai> call, Response<ResponseTambahSelesai> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahSelesai> call, Throwable t) {
                Toast.makeText(context, "onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataKerjaan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtKerjaan, txtWaktu;
        Button btnSelesai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKerjaan = itemView.findViewById(R.id.txt_kerjaan);
            txtWaktu = itemView.findViewById(R.id.txt_waktu_kerjaan);
            btnSelesai = itemView.findViewById(R.id.btn_selesai);
        }
    }
}
