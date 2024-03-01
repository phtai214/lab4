package com.example.loginfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.loginfragment.fragment.HomeFragment;
import com.example.loginfragment.fragment.InfoFragment;
import com.example.loginfragment.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnBottom = findViewById(R.id.navMenu);

        loadFragment(new HomeFragment());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mnBottom.setOnItemSelectedListener(getListener());
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        }
        }
    }






    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home){
            finish();
            return  true;
        }
        return true;
    }



    void loadFragment(Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment , fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

}
