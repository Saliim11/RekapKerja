package com.example.rekapkerja.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.admin.adminhome.AdminHomeFragment;
import com.example.rekapkerja.activity.admin.adminhome.AdminSettingsFragment;
import com.example.rekapkerja.activity.admin.adminhome.StaffListFragment;
import com.example.rekapkerja.activity.staff.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // TODO load default fragment
        loadFragment(new HomeFragment());

        BottomNavigationView navigationView = findViewById(R.id.admin_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.admin_fragment_container, fragment)
                    .commit();
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new AdminHomeFragment();
                break;
            case R.id.navigation_chart_home:
                fragment = new StaffListFragment();
                break;
            case R.id.navigation_setting_home:
                fragment = new AdminSettingsFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
