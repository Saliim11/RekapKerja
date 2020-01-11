package com.example.rekapkerja.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rekapkerja.R;
import com.example.rekapkerja.model.register.ResponseRegister;
import com.example.rekapkerja.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edtNameRegister)
    EditText edtNameRegister;
    @BindView(R.id.edtUserRegister)
    EditText edtUserRegister;
    @BindView(R.id.edtPassRegister)
    EditText edtPassRegister;
    @BindView(R.id.radio_guru)
    RadioButton radioGuru;
    @BindView(R.id.radio_musyrif)
    RadioButton radioMusyrif;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.txtToLogin)
    TextView txtToLogin;
    @BindView(R.id.spinClass)
    Spinner spinClass;

    String[] kelas = {"SMK 12", "SMK 11", "SMK 10", "SMP 9", "SMP 8", "SMP 7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, kelas);
        spinClass.setAdapter(adapter);
    }

    @OnClick({R.id.btnRegister, R.id.txtToLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:

                String nama = edtNameRegister.getText().toString();
                String username = edtUserRegister.getText().toString();
                String password = edtPassRegister.getText().toString();
                String leveluser = "";
                String kelasuser = spinClass.getSelectedItem().toString();

                if (radioGuru.isChecked()) {
                    leveluser += "Guru";
                }
                if (radioMusyrif.isChecked()) {
                    leveluser += "Musyrif";
                }

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    userRegister(nama, username, password, leveluser, kelasuser);
                }

                break;
            case R.id.txtToLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void userRegister(String paramNama, String paramUsername, String paramPassword, String paramLevelUser, String paramKelas) {
        ApiClient.service.responseRegister(paramNama, paramUsername, paramPassword, paramLevelUser, paramKelas).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "FailedOnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
