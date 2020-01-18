package com.example.rekapkerja.activity.admin.adminhome;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.admin.AdminActivity;
import com.example.rekapkerja.model.tambahtask.ResponseTambahTask;
import com.example.rekapkerja.network.ApiClient;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTaskActivity extends AppCompatActivity {

    @BindView(R.id.edtTaskName)
    EditText edtTaskName;
    @BindView(R.id.edtTaskTime)
    EditText edtTaskTime;
    @BindView(R.id.btnAddTask)
    Button btnAddTask;
    @BindView(R.id.radio_task_guru)
    RadioButton radioTaskGuru;
    @BindView(R.id.radio_task_musyrif)
    RadioButton radioTaskMusyrif;
    @BindView(R.id.spinTaskDate)
    Spinner spinTaskDate;
    @BindView(R.id.spinPoint)
    Spinner spinPoint;

    String hari;

    String[] harian = {"Ahad", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
    String[] hari_value = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] point = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_task);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, harian);
        spinTaskDate.setAdapter(adapter);

        ArrayAdapter<String> adapterPoint = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, point);
        spinPoint.setAdapter(adapterPoint);

        spinTaskDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        edtTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TambahTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtTaskTime.setText(selectedHour + ":" + selectedMinute + ":00");
                    }
                }, hour, minute, true); //Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    @OnClick(R.id.btnAddTask)
    public void onViewClicked() {

        String namakerjaan = edtTaskName.getText().toString();
        String levelkerjaan = "";
        String waktukerjaan = edtTaskTime.getText().toString();
        String poinkerjaan = spinPoint.getSelectedItem().toString();

        if (radioTaskGuru.isChecked()) {
            levelkerjaan += "Guru";
        }
        if (radioTaskMusyrif.isChecked()) {
            levelkerjaan += "Musyrif";
        }

        if (TextUtils.isEmpty(namakerjaan) || TextUtils.isEmpty(waktukerjaan) || TextUtils.isEmpty(poinkerjaan)) {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            addEmployeeTask(namakerjaan, levelkerjaan, hari, waktukerjaan, poinkerjaan);
        }
    }

    private void addEmployeeTask(String paramNamaKerjaan, String paramLevelKerjaan, String paramHariKerjaan, String paramWaktuKerjaan, String paramPoinKerjaan) {
        ApiClient.service.responseTambahTask(paramNamaKerjaan, paramLevelKerjaan, paramHariKerjaan, paramWaktuKerjaan, paramPoinKerjaan).enqueue(new Callback<ResponseTambahTask>() {
            @Override
            public void onResponse(Call<ResponseTambahTask> call, Response<ResponseTambahTask> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(TambahTaskActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahTaskActivity.this, AdminActivity.class));
                        finish();
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(TambahTaskActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahTask> call, Throwable t) {
                Toast.makeText(TambahTaskActivity.this, "FailedOnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
