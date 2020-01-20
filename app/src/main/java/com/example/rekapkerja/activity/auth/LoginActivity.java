package com.example.rekapkerja.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.MainActivity;
import com.example.rekapkerja.activity.admin.AdminActivity;
import com.example.rekapkerja.model.login.ResponseLogin;
import com.example.rekapkerja.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static String iduser;
    public static String namauser;
    public static String leveluser;
    public static String kelasuser;

    @BindView(R.id.edtUserLogin)
    EditText edtUserLogin;
    @BindView(R.id.edtPassLogin)
    EditText edtPassLogin;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtToRegister)
    TextView txtToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {

        String username = edtUserLogin.getText().toString();
        String password = edtPassLogin.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            userLogin(username, password);
        }
    }

    private void userLogin(String paramUsername, String paramPassword) {
        ApiClient.service.responseLogin(paramUsername, paramPassword).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin user = response.body();

                if (user.getResult().equalsIgnoreCase("1")) {
                    String message = response.body().getMsg();
//                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    iduser = user.getUser().getIdUser();
                    namauser = user.getUser().getNamaUser();
                    leveluser = user.getUser().getLevelUser();
                    kelasuser = user.getUser().getKelasUser();

                    if (leveluser.equals("Pimpinan")) {
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed on Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.txtToRegister)
    public void onToRegisterClicked() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
