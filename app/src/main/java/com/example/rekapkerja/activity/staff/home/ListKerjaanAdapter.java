package com.example.rekapkerja.activity.staff.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekapkerja.R;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaanStaff;

import java.util.ArrayList;
import java.util.List;

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

        String namaKerjaan = dataKerjaan.get(position).getNamaKerjaan();
        String waktuKerjaan = dataKerjaan.get(position).getWaktuKerjaan();

        holder.txtKerjaan.setText(namaKerjaan);
        holder.txtWaktu.setText(waktuKerjaan);
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
