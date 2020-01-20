package com.example.rekapkerja.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekapkerja.R;
import com.example.rekapkerja.model.listKerjaan.ResponseListKerjaan;
import com.example.rekapkerja.activity.admin.adminhome.UDKerjaanActivity;

import java.util.List;

public class ListKerjaanStaffAdapter extends RecyclerView.Adapter<ListKerjaanStaffAdapter.ViewHolder> {

    Context context;
    List<ResponseListKerjaan> dataGetKerjaan;

    public ListKerjaanStaffAdapter(Context context, List<ResponseListKerjaan> dataGetKerjaan) {
        this.context = context;
        this.dataGetKerjaan = dataGetKerjaan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kerjaan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String hariKerjaan = dataGetKerjaan.get(position).getHariKerjaan();
        String waktuKerjaan = dataGetKerjaan.get(position).getWaktuKerjaan();
        String namaKerjaan = dataGetKerjaan.get(position).getNamaKerjaan();
        String levelKerjaan = dataGetKerjaan.get(position).getLevelKerjaan();
        String poinKerjaan = dataGetKerjaan.get(position).getPoinKerjaan();
        String idKerjaan = dataGetKerjaan.get(position).getIdKerjaan();

        holder.txtHari.setText(hariKerjaan);
        holder.txtWaktu.setText(waktuKerjaan);
        holder.txtNama.setText(namaKerjaan);
        holder.txtLevel.setText(levelKerjaan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UDKerjaanActivity.class);
                intent.putExtra(UDKerjaanActivity.KEY_ID, idKerjaan);
                intent.putExtra(UDKerjaanActivity.KEY_NAMA, namaKerjaan);
                intent.putExtra(UDKerjaanActivity.KEY_LEVEL, levelKerjaan);
                intent.putExtra(UDKerjaanActivity.KEY_HARI, hariKerjaan);
                intent.putExtra(UDKerjaanActivity.KEY_WAKTU, waktuKerjaan);
                intent.putExtra(UDKerjaanActivity.KEY_POIN, poinKerjaan);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataGetKerjaan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHari, txtWaktu, txtNama, txtLevel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHari = itemView.findViewById(R.id.txtHari);
            txtWaktu = itemView.findViewById(R.id.txtWaktu);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtLevel = itemView.findViewById(R.id.txtLevel);
        }
    }
}
