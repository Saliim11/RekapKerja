package com.saliim.rekapkerja.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.admin.adminstaff.StaffDetailActivity;
import com.saliim.rekapkerja.model.getuser.ResponseGetUsers;

import java.util.ArrayList;
import java.util.List;

public class ListStaffAdapter extends RecyclerView.Adapter<ListStaffAdapter.ViewHolder> {

    Context context;
    List<ResponseGetUsers> dataStaff;

    public ListStaffAdapter(Context context, ArrayList<ResponseGetUsers> dataStaff) {
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
        String userStaff = dataStaff.get(position).getUsernameUser();
        String kelasStaff = dataStaff.get(position).getKelasUser();
        String idStaff = dataStaff.get(position).getIdUser();

        holder.txtNamaStaff.setText(namaStaff);
        holder.txtLevelStaff.setText(levelStaff);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StaffDetailActivity.class);
                intent.putExtra("id", idStaff);
                intent.putExtra("nama", namaStaff);
                intent.putExtra("username", userStaff);
                intent.putExtra("level", levelStaff);
                intent.putExtra("kelas", kelasStaff);
                context.startActivity(intent);
            }
        });
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
