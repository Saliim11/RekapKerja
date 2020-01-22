package com.saliim.rekapkerja.activity.admin.adminhome;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.model.deleteuser.ResponseDeleteUser;
import com.saliim.rekapkerja.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffDetailActivity extends AppCompatActivity {

    @BindView(R.id.txtNamaStaff)
    TextView txtNamaStaff;
    @BindView(R.id.txtUserStaff)
    TextView txtUserStaff;
    @BindView(R.id.txtKelasStaff)
    TextView txtKelasStaff;
    @BindView(R.id.txtStaffLevel)
    TextView txtStaffLevel;
    @BindView(R.id.btnPecat)
    Button btnPecat;

    String idStaff;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        idStaff = intent.getStringExtra("id");
        String namaStaff = intent.getStringExtra("nama");
        String userStaff = intent.getStringExtra("username");
        String levelStaff = intent.getStringExtra("level");
        String kelasStaff = intent.getStringExtra("kelas");

        txtNamaStaff.setText(namaStaff);
        txtUserStaff.setText("Username : " + userStaff);
        txtKelasStaff.setText("Kelas : " + kelasStaff);
        txtStaffLevel.setText("Level : " + levelStaff);
    }

    @OnClick(R.id.btnPecat)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StaffDetailActivity.this);
        builder.setMessage("Ingin Memecat Orang Ini ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Sangat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ApiClient.service.responseDeleteUser(idStaff).enqueue(new Callback<ResponseDeleteUser>() {
                    @Override
                    public void onResponse(Call<ResponseDeleteUser> call, Response<ResponseDeleteUser> response) {
                        if (response.isSuccessful()) {
                            String message = response.body().getMessage();
                            int status = response.body().getStatus();

                            if (status == 1) {
                                Toast.makeText(StaffDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (status == 0) {
                                Toast.makeText(StaffDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDeleteUser> call, Throwable t) {

                    }
                });
            }
        });

        builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
