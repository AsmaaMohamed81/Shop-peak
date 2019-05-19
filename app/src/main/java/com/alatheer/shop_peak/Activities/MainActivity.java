package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Adapter.NavigationAdapter;
import com.alatheer.shop_peak.Adapter.OnBackPressedListener;
import com.alatheer.shop_peak.Adapter.Search_Navigation_Adapter;
import com.alatheer.shop_peak.BuildConfig;
import com.alatheer.shop_peak.Fragments.Client_Profile_Fragment;
import com.alatheer.shop_peak.Fragments.Favorite_Fragment;
import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Fragments.NotificationFragment;
import com.alatheer.shop_peak.Fragments.ProfileFragment;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.NavigationModel;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView, navigationView2;
    ImageView img_menu;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    NavigationAdapter navigationAdapter;
    Search_Navigation_Adapter search_navigation_adapter;
    RecyclerView.LayoutManager navigation_manager;
    RecyclerView navigationrecycler;
    CircleImageView img;
    android.app.Fragment selectedfragment;
    HomeFragment homeFragment;
    MySharedPreference mPrefs;
    ProfileFragment profileFragment;
    EditText search;
    HomeAdapter homeAdapter;
    String title1;
    TextView login_register, tv_username;
    List<HomeModel> homeModels;
    OnBackPressedListener onBackPressedListener;
    private int PICK_IMAGE_FROM_GALEARY_REQUEST=0;
    Uri uri;
    Bitmap bitmap;
    int flag=0;
    UserModel userModel;
    Favorite_Database favoriteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void initview() {
        final FragmentManager fm = getSupportFragmentManager();
        img_menu = findViewById(R.id.menu_img);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView2 = findViewById(R.id.nav_view2);
        search = findViewById(R.id.txt_search);
        login_register = findViewById(R.id.tv_login_register);
        View headview=navigationView.getHeaderView(0);
        img = headview.findViewById(R.id.profile_img);
        tv_username = headview.findViewById(R.id.txtname);
        login_register = headview.findViewById(R.id.tv_login_register);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Search_Activity.class);
                i.putExtra("title", search.getText().toString());
                i.putExtra("list", (Serializable) homeModels);
                startActivity(i);
            }
        });
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login_Activity.class));
            }
        });
        try{
            // String image_url=i.getStringExtra("image_url");
            //Log.e("dddd",image_url);
            //String personname=i.getStringExtra("personName");
            //Toast.makeText(this, personname, Toast.LENGTH_SHORT).show();
            mPrefs = new MySharedPreference(this);
            userModel = mPrefs.Get_UserData(MainActivity.this);
            String name=userModel.getName();
            String url=userModel.getImage_url();
            tv_username.setText(name);
            Picasso.with(this).load(url).into(img);
            tv_username.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            login_register.setVisibility(View.GONE);
            if (!isConnected()) {
                new AlertDialog.Builder(this).setIcon(R.drawable.ic_warning).setTitle(getString(R.string.networkconnectionAlert))
                        .setMessage(getString(R.string.check_connection))
                        .setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).show();
            } else {
                Toast.makeText(this, "welcom" + userModel.getName(), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            //String personname=i.getStringExtra("personName");
            //img.setImageResource(R.mipmap.icon_round);
            //textView.setText(personname);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseimage();
            }
        });
        initRecyclerview();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav_listner);
        HomeFragment homeFragment=new HomeFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    }

    private void chooseimage() {

    }

    public void setSelectProfile(String vender_name,int vender_image,String image1,String image2){
        if (profileFragment==null)
        {
            profileFragment = profileFragment.getInstance();
        }
        Bundle bundle =new Bundle();
        bundle.putString("name",vender_name);
        bundle.putInt("image",vender_image);
        bundle.putString("image1",image1);
        bundle.putString("image2",image2);
        profileFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
    }
    public void initRecyclerview(){
        navigationrecycler=findViewById(R.id.navigation_recycler_list);
        navigationrecycler.setHasFixedSize(true);
        navigation_manager=new LinearLayoutManager(this);
        navigationrecycler.setLayoutManager(navigation_manager);
        navigationrecycler.setNestedScrollingEnabled(false);
        navigationAdapter=new NavigationAdapter(navigationModelList(),this);
        navigationrecycler.setAdapter(navigationAdapter);
    }

    public void passdata(final String title, final List<HomeModel> homeModelList) {
        title1 = title;
        homeModels = homeModelList;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedfragment= null;
            switch (item.getItemId()){
                case R.id.nav_notification:
                    selectedfragment=new NotificationFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    break;
                case R.id.nav_home:
                    selectedfragment=new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    break;
                case R.id.nav_favorite:
                    selectedfragment = new Favorite_Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    break;
                case R.id.nav_profile:
                    selectedfragment=new Client_Profile_Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    break;
                // case R.id.nav_add:
                //startActivity(new Intent(MainActivity.this,AddProductActivity.class));
                //break;
            }
            return true;
        }
    };
   /* public void chooseimage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGE_FROM_GALEARY_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_FROM_GALEARY_REQUEST&&resultCode==RESULT_OK&&data!=null) {
            Log.v("kkkkkk","mohamed");
            uri = data.getData();
            /*Bundle bundle=new Bundle();
            bundle.putString("imageUri",uri.toString());
            bundle.putInt("flag",flag);
            ProfileFragment profileFragment=new ProfileFragment();
            profileFragment.setArguments(bundle);
            try {
                Log.v("sssss","mohamed");
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ProfileModel profileModel = new ProfileModel(bitmap);
                List<ProfileModel>profileModels=new ArrayList<>();
                profileModels.add(profileModel);
                ProfileFragment profileFragment=new ProfileFragment();
                Prof
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }*/


    public void showmenu(View view) {
        navigationView2.setNavigationItemSelectedListener(this);
        openDrawer();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String itemName = (String) item.getTitle();
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_logout:
                mPrefs.ClearData(MainActivity.this);
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity.this, Login_Activity.class));

                Animatoo.animateInAndOut(MainActivity.this);
                break;
            case R.id.nav_join_us:
                startActivity(new Intent(MainActivity.this, Vender_Signup_Activity.class));
                Animatoo.animateInAndOut(MainActivity.this);
                break;
            case R.id.nav_contact_us:
                startActivity(new Intent(MainActivity.this, Contact_Us_Activity.class));
                Animatoo.animateInAndOut(MainActivity.this);
                break;

            case R.id.nav_share:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SHOP_PEAK");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;
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
    private List<NavigationModel> navigationModelList (){

        List<NavigationModel>  navigationlist = new ArrayList<>();
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("سوبر ماركت",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("كمبوتر واكسسورات",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المطبخ والسفره",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المنزل والديكور",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("سوبر ماركت",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("كمبوتر واكسسورات",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المطبخ والسفره",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المنزل والديكور",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("سوبر ماركت",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("كمبوتر واكسسورات",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المطبخ والسفره",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المنزل والديكور",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("سوبر ماركت",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("كمبوتر واكسسورات",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المطبخ والسفره",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("موبيلات واجهزه",R.drawable.ic_favorite_sold));
        navigationlist.add(new NavigationModel("المنزل والديكور",R.drawable.ic_favorite_sold));

        return navigationlist;
    }

    public void sendHomeItem(String[] image, String title, String des, String price, String gender) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", image);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("title", title);
        intent.putExtra("des", des);
        intent.putExtra("price", price);
        intent.putExtra("gender",gender);
        startActivity(intent);
        Animatoo.animateInAndOut(MainActivity.this);

    }

    public void sendOfferItem(String[] image_resources, String title, String des, String price, String gender) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("homeimage", image_resources);
        Intent intent = new Intent(this, Offer_Details_Activity.class);
        intent.putExtras(bundle);
        intent.putExtra("title", title);
        intent.putExtra("des", des);
        intent.putExtra("price", price);
        intent.putExtra("gender", gender);
        startActivity(intent);
        Animatoo.animateInAndOut(MainActivity.this);
    }
    @Override
    public void onBackPressed() {
        selectedfragment=new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }


}
