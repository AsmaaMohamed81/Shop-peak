package com.alatheer.shop_peak.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Fragments.NotificationFragment;
import com.alatheer.shop_peak.Fragments.SettingFragment;
import com.alatheer.shop_peak.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img_menu;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();


    }

    private void initview() {

        img_menu = findViewById(R.id.menu_img);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav_listner);

        HomeFragment homeFragment = new HomeFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragment= null;
            switch (item.getItemId()){
                case R.id.nav_notification:
                    selectedfragment=new NotificationFragment();
                    break;
                case R.id.nav_home:
                     selectedfragment=new HomeFragment();
                     break;
                case R.id.nav_setting:
                     selectedfragment=new SettingFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
            return true;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }
    public void showmenu(View view) {
        navigationView.setNavigationItemSelectedListener(this);
        openDrawer();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String itemName = (String) item.getTitle();
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_fav:
                Toast.makeText(this, itemName, Toast.LENGTH_SHORT).show();
            case R.id.nav_share :
                Toast.makeText(this, itemName, Toast.LENGTH_LONG).show();
            case R.id.nav_setting :
                Toast.makeText(this, itemName, Toast.LENGTH_LONG).show();
        }

        closeDrawer();
        return true;
    }
    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

}
