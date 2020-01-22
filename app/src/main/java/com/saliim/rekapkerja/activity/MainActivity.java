package com.saliim.rekapkerja.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.saliim.rekapkerja.R;
import com.saliim.rekapkerja.activity.staff.chart.ChartFragment;
import com.saliim.rekapkerja.activity.staff.history.HistoryFragment;
import com.saliim.rekapkerja.activity.staff.home.HomeFragment;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(MainActivity.this, PengaturanActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            case R.id.navigation_history:
                fragment = new HistoryFragment();
                break;
        }
        return loadFragment(fragment);
    }


}
