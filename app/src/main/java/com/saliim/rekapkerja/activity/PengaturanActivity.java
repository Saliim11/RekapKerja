package com.saliim.rekapkerja.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.auth.LoginActivity;
import com.saliim.rekapkerja.model.updateuser.ResponseUpdateUser;
import com.saliim.rekapkerja.network.ApiClient;
import com.saliim.rekapkerja.utils.SaveSharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class PengaturanActivity extends AppCompatActivity {

    EditText edtUpdateUser, edtUpdatePassword;

    @BindView(R.id.txtFirstName)
    TextView txtFirstName;
    @BindView(R.id.txtFullName)
    TextView txtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        ButterKnife.bind(this);

        String name = LoginActivity.namaUser;
        String lastName = "";
        String firstName= "";
        if(name.split("\\w+").length>1){

            lastName = name.substring(name.lastIndexOf(" ")+1);
            firstName = name.substring(0, name.lastIndexOf(' '));
        }
        else{
            firstName = name;
        }
        Log.d("nama2", name+"\n"+firstName+"\n"+lastName);

        txtFirstName.setText("Hello! "+firstName);
        txtFullName.setText(name);

    }

    @OnClick({R.id.btnGantiUsername, R.id.btnGantiNama, R.id.btnAdminLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGantiUsername:
                dialogForm();
                break;
            case R.id.btnGantiNama:
                dialogForm2();
            case R.id.btnAdminLogout:
                logoutDialog();
                break;
        }
    }

    private void dialogForm() {
        String usernameUser = LoginActivity.usernameUser;
        AlertDialog.Builder dialog = new AlertDialog.Builder(PengaturanActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Update User");

        edtUpdateUser = dialogView.findViewById(R.id.edtUpdateUser);
        edtUpdatePassword = dialogView.findViewById(R.id.edtUpdatePassword);

        edtUpdateUser.setText(usernameUser);

        dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String username = edtUpdateUser.getText().toString();
                String password = edtUpdatePassword.getText().toString();
                String nama = LoginActivity.namaUser;
                String level = LoginActivity.levelUser;
                String kelas = LoginActivity.kelasUser;

                updateUser(nama, username, password, level, kelas);
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

    private void dialogForm2() {

    }

    private void logoutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PengaturanActivity.this);
        dialog.setTitle("Peringatan!");
        dialog.setMessage("Anda yakin ingin logout");
        dialog.setCancelable(true);
        dialog.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SaveSharedPreference.setLoggedOut(PengaturanActivity.this);
                startActivity(new Intent(PengaturanActivity.this, LoginActivity.class));
                finish();
            }
        });
        dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void updateUser(String nama, String username, String password, String level, String kelas) {

        String id = LoginActivity.idUser;

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
}
