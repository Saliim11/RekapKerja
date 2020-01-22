package com.saliim.rekapkerja.activity.admin.adminhome;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.model.deletekerjaan.ResponseDeleteKerjaan;
import com.saliim.rekapkerja.model.updatekerjaan.ResponseUpdateKerjaan;
import com.saliim.rekapkerja.network.ApiClient;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UDKerjaanActivity extends AppCompatActivity {

    @BindView(R.id.edtUpdateTaskName)
    EditText edtUpdateTaskName;
    @BindView(R.id.radio_update_task_guru)
    RadioButton radioUpdateTaskGuru;
    @BindView(R.id.radio_update_task_musyrif)
    RadioButton radioUpdateTaskMusyrif;
    @BindView(R.id.spinUpdateTaskDate)
    Spinner spinUpdateTaskDate;
    @BindView(R.id.edtUpdateTaskTime)
    EditText edtUpdateTaskTime;
    @BindView(R.id.spinUpdatePoint)
    Spinner spinUpdatePoint;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    public static String KEY_ID = "key_id";
    public static String KEY_NAMA = "key_nama";
    public static String KEY_LEVEL = "key_level";
    public static String KEY_HARI = "key_hari";
    public static String KEY_WAKTU = "key_waktu";
    public static String KEY_POIN = "key_poin";

    String terimaId;

    String hari;

    String[] harian = {"Ahad", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
    String[] hari_value = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] point = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udkerjaan);
        ButterKnife.bind(this);

        terimaId = getIntent().getStringExtra(KEY_ID);
        String terimaNama = getIntent().getStringExtra(KEY_NAMA);
        String terimaLevel = getIntent().getStringExtra(KEY_LEVEL);
        String terimaHari = getIntent().getStringExtra(KEY_HARI);
        String terimaWaktu = getIntent().getStringExtra(KEY_WAKTU);
        String terimaPoin = getIntent().getStringExtra(KEY_POIN);

        edtUpdateTaskName.setText(terimaNama);
        edtUpdateTaskTime.setText(terimaWaktu);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, harian);
        spinUpdateTaskDate.setAdapter(adapter);

        ArrayAdapter<String> adapterPoint = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, point);
        spinUpdatePoint.setAdapter(adapterPoint);

        spinUpdateTaskDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String isi_hari = hari_value[i];
                hari = isi_hari;
                Log.d("isihari", hari);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtUpdateTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(UDKerjaanActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtUpdateTaskTime.setText(selectedHour + ":" + selectedMinute + ":00");
                    }
                }, hour, minute, true); //Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        if (terimaLevel.equals("Musyrif")) {
            radioUpdateTaskMusyrif.setChecked(true);
        } else if (terimaLevel.equals("Guru")) {
            radioUpdateTaskGuru.setChecked(true);
        }
    }

    @OnClick({R.id.btnUpdate, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnUpdate:

                String nama = edtUpdateTaskName.getText().toString();
                String level = "";
                String waktu = edtUpdateTaskTime.getText().toString();
                String poin = spinUpdatePoint.getSelectedItem().toString();

                if (radioUpdateTaskGuru.isChecked()) {
                    level += "Guru";
                }
                if (radioUpdateTaskMusyrif.isChecked()) {
                    level += "Musyrif";
                }

                updateKerjaan(terimaId, nama, level, hari, waktu, poin);

                break;
            case R.id.btnDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(UDKerjaanActivity.this);
                builder.setMessage("Ingin Menghapus Kerjaan Ini ?");
                builder.setCancelable(true);

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApiClient.service.responseDeleteKerjaan(terimaId).enqueue(new Callback<ResponseDeleteKerjaan>() {
                            @Override
                            public void onResponse(Call<ResponseDeleteKerjaan> call, Response<ResponseDeleteKerjaan> response) {
                                if (response.isSuccessful()) {
                                    String message = response.body().getMessage();
                                    int status = response.body().getStatus();

                                    if (status == 1) {
                                        Toast.makeText(UDKerjaanActivity.this, message, Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else if (status == 0) {
                                        Toast.makeText(UDKerjaanActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDeleteKerjaan> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
        }
    }

    private void updateKerjaan(String terimaId, String nama, String level, String hari, String waktu, String poin) {
        ApiClient.service.responseUpdateKerjaan(terimaId, nama, level, hari, waktu, poin).enqueue(new Callback<ResponseUpdateKerjaan>() {
            @Override
            public void onResponse(Call<ResponseUpdateKerjaan> call, Response<ResponseUpdateKerjaan> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(UDKerjaanActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(UDKerjaanActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateKerjaan> call, Throwable t) {
                Toast.makeText(UDKerjaanActivity.this, "Gagal onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
