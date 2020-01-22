package com.saliim.rekapkerja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.model.kerjaanSelesai.ResponseListSelesai;

import java.util.ArrayList;
import java.util.List;

public class ListSelesaiAdapter extends RecyclerView.Adapter<ListSelesaiAdapter.ViewHolder> {

    Context context;
    List<ResponseListSelesai> dataSelesai;

    public ListSelesaiAdapter(Context context, ArrayList<ResponseListSelesai> dataSelesai){
        this.context = context;
        this.dataSelesai = dataSelesai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_kerjaan_selesai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nama = dataSelesai.get(position).getKerjaanSelesai();
        String poin = dataSelesai.get(position).getPoinSelesai();
        String waktu = dataSelesai.get(position).getTglSelesai();

        holder.txtKerjaanSelesai.setText(nama);
        holder.txtPoinSelesai.setText(poin);
        holder.txtWaktuSelesai.setText(waktu);

    }

    @Override
    public int getItemCount() {
        return dataSelesai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtKerjaanSelesai, txtPoinSelesai, txtWaktuSelesai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKerjaanSelesai = itemView.findViewById(R.id.txtKerjaanSelesai);
            txtPoinSelesai = itemView.findViewById(R.id.txtPoinSelesai);
            txtWaktuSelesai = itemView.findViewById(R.id.txtWaktuSelesai);
        }
    }
}
