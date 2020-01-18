package com.example.rekapkerja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekapkerja.R;
import com.example.rekapkerja.model.getuser.ResponseGetUsers;

import java.util.ArrayList;
import java.util.List;

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.ViewHolder> {

    Context context;
    List<ResponseGetUsers> dataStaff;

    public StaffListAdapter(Context context, ArrayList<ResponseGetUsers> dataStaff) {
        this.context = context;
        this.dataStaff = dataStaff;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_staff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String namaStaff = dataStaff.get(position).getNamaUser();
        String levelStaff = dataStaff.get(position).getLevelUser();

        holder.txtNamaStaff.setText(namaStaff);
        holder.txtLevelStaff.setText(levelStaff);
    }

    @Override
    public int getItemCount() {
        return dataStaff.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaStaff, txtLevelStaff;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaStaff = itemView.findViewById(R.id.txtNamaStaff);
            txtLevelStaff = itemView.findViewById(R.id.txtLevelStaff);
        }
    }
}
