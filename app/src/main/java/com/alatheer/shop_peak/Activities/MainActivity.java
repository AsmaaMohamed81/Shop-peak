package com.alatheer.shop_peak.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Adapter.ImageAdapter;
import com.alatheer.shop_peak.Model.ImageModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     List<ImageModel>imageModels;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();


    }

    private void initview() {
        recyclerView=findViewById(R.id.recycler);
        img_menu=findViewById(R.id.menu_img);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        ImageModel imageModel1=new ImageModel();
        imageModel1.setImage(R.drawable.logo);
        ImageModel imageModel2=new ImageModel();
        imageModel2.setImage(R.drawable.logo);
        ImageModel imageModel3=new ImageModel();
        imageModel3.setImage(R.drawable.logo);
        ImageModel imageModel4=new ImageModel();
        imageModel4.setImage(R.drawable.logo);
        ImageModel imageModel5=new ImageModel();
        imageModel5.setImage(R.drawable.logo);
        ImageModel imageModel6=new ImageModel();
        imageModel6.setImage(R.drawable.logo);
        ImageModel imageModel7=new ImageModel();
        imageModel7.setImage(R.drawable.logo);
        imageModels=new ArrayList<>();
        imageModels.add(imageModel1);
        imageModels.add(imageModel2);
        imageModels.add(imageModel3);
        imageModels.add(imageModel4);
        imageModels.add(imageModel5);
        imageModels.add(imageModel6);
        imageModels.add(imageModel7);
        ImageAdapter imageAdapter=new ImageAdapter(imageModels,this);
        recyclerView.setAdapter(imageAdapter);
        HomeFragment homeFragment=new HomeFragment();
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame1,homeFragment).commit();

    }

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
