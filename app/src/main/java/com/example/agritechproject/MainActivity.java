package com.example.agritechproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.btn_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder,new DashbordFragment()).commit();

    }
    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment unFragment=null;
            switch (item.getItemId()){

                case R.id.item_dashbord :
                    unFragment=new DashbordFragment();
                    break;

                case R.id.item_farms:
                    unFragment=new FarmsListFragment();
                    break;
                case R.id.item_person:
                    unFragment=new AccountFragment();
                    break;



            }

            String URL="http://192.168.1.35:80/";
            Bundle bundle=new Bundle();
            bundle.putString("url",URL);
            unFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder,unFragment).commit();
            return true;
        }
    };
}