package com.example.rekapkerja.activity.admin.adminmenu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.MainActivity;
import com.example.rekapkerja.activity.auth.LoginActivity;
import com.example.rekapkerja.model.updateuser.ResponseUpdateUser;
import com.example.rekapkerja.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaturanActivity extends AppCompatActivity {

    @BindView(R.id.btnChangeUsername)
    Button btnChangeUsername;
    @BindView(R.id.btnAdminLogout)
    Button btnAdminLogout;

    EditText edtUpdateUser, edtUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnChangeUsername, R.id.btnAdminLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChangeUsername:
                dialogForm();
                break;
            case R.id.btnAdminLogout:
                break;
        }
    }

    private void dialogForm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PengaturanActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Update User");

        edtUpdateUser = dialogView.findViewById(R.id.edtUpdateUser);
        edtUpdatePassword = dialogView.findViewById(R.id.edtUpdatePassword);

        dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String username = edtUpdateUser.getText().toString();
                String password = edtUpdatePassword.getText().toString();
                String nama = LoginActivity.namauser;
                String level = LoginActivity.leveluser;
                String kelas = LoginActivity.kelasuser;

                updateUser(nama, username, password, level, kelas);
            }

            private void updateUser(String nama, String username, String password, String level, String kelas) {

                String id = LoginActivity.iduser;

                ApiClient.service.responseUpdateUser(id, nama, username, password, level, kelas).enqueue(new Callback<ResponseUpdateUser>() {
                    @Override
                    public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
                        if (response.isSuccessful()) {
                            String message = response.body().getMessage();
                            String status = response.body().getStatus();

                            if (message.equalsIgnoreCase("update sukses")) {
                                Toast.makeText(PengaturanActivity.this, message, Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (status.equalsIgnoreCase("0")) {
                                Toast.makeText(PengaturanActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdateUser> call, Throwable t) {
                        Toast.makeText(PengaturanActivity.this, "Gagal On Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
}
