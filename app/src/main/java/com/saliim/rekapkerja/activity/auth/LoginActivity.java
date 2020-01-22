package com.saliim.rekapkerja.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.MainActivity;
import com.saliim.rekapkerja.activity.admin.AdminActivity;
import com.saliim.rekapkerja.model.login.ResponseLogin;
import com.saliim.rekapkerja.network.ApiClient;
import com.saliim.rekapkerja.utils.SaveSharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.saliim.rekapkerja.utils.PreferencesUtility.ID_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.NAMA_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.PASSWORD_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.USERNAME_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.LEVEL_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.KELAS_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.LOGGED_IN_PREF;

public class LoginActivity extends AppCompatActivity {

    public static String idUser;
    public static String namaUser;
    public static String usernameUser;
    public static String levelUser;
    public static String kelasUser;
    public static String passwordUser;

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

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext(), LOGGED_IN_PREF)){
            idUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), ID_PREF);
            namaUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), NAMA_PREF);
            usernameUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), USERNAME_PREF);
            passwordUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), PASSWORD_PREF);
            levelUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), LEVEL_PREF);
            kelasUser = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), KELAS_PREF);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {

        String username = edtUserLogin.getText().toString();
        String password = edtPassLogin.getText().toString();

        passwordUser = password;

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

                    idUser = user.getUser().getIdUser();
                    namaUser = user.getUser().getNamaUser();
                    usernameUser = user.getUser().getUsernameUser();
                    levelUser = user.getUser().getLevelUser();
                    kelasUser = user.getUser().getKelasUser();

                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true, kelasUser, levelUser, passwordUser, usernameUser, namaUser, idUser);

                    if (levelUser.equals("Pimpinan")) {
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
