package com.example.rekapkerja.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.staff.ChartFragment;
import com.example.rekapkerja.activity.staff.HomeFragment;
import com.example.rekapkerja.activity.staff.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO load default fragment
        loadFragment(new HomeFragment());

        BottomNavigationView navigationView = findViewById(R.id.home_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_fragment_container, fragment)
                    .commit();
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_chart_home:
                fragment = new ChartFragment();
                break;
            case R.id.navigation_setting_home:
                fragment = new SettingsFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
