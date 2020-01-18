package com.example.rekapkerja.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.rekapkerja.R;
import com.example.rekapkerja.activity.admin.adminhome.AdminHomeFragment;
import com.example.rekapkerja.activity.admin.adminhome.StaffListFragment;
import com.example.rekapkerja.activity.staff.home.HomeFragment;
import com.example.rekapkerja.activity.admin.adminmenu.PengaturanAdminActivity;
import com.example.rekapkerja.activity.admin.adminmenu.TentangAdminActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // TODO load default fragment
        loadFragment(new AdminHomeFragment());

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
            case R.id.navigation_admin_home:
                fragment = new AdminHomeFragment();
                break;
            case R.id.navigation_staff_list_admin:
                fragment = new StaffListFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(AdminActivity.this, PengaturanAdminActivity.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(AdminActivity.this, TentangAdminActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
