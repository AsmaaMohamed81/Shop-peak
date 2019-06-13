package com.alatheer.shop_peak.Activities;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Adapter.NavigationAdapter;
import com.alatheer.shop_peak.Adapter.Search_Navigation_Adapter;
import com.alatheer.shop_peak.Fragments.Client_Profile_Fragment;
import com.alatheer.shop_peak.Fragments.Favorite_Fragment;
import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Fragments.NotificationFragment;
import com.alatheer.shop_peak.Fragments.ProfileFragment;
import com.alatheer.shop_peak.Fragments.ProfileVendor_Fragment;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.NavigationModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView, navigationView2;
    ImageView img_menu;
    Toolbar toolbar;
    MyAppDatabase myAppDatabase;
    BottomNavigationView bottomNavigationView, bottomNavigationView2;
    NavigationAdapter navigationAdapter;
    Search_Navigation_Adapter search_navigation_adapter;
    RecyclerView.LayoutManager navigation_manager;
    RecyclerView navigationrecycler;
    CircleImageView profile_img;
    android.app.Fragment selectedfragment;
    HomeFragment homeFragment;
    MySharedPreference mPrefs;
    ProfileFragment profileFragment;
    EditText search;
    HomeAdapter homeAdapter;
    String title1;
    TextView login_register, tv_username;
    List<HomeModel> homeModels;
    String sanf_name;
    private int PICK_IMAGE_FROM_GALEARY_REQUEST=0;
    Uri uri;
    Bitmap bitmap;
    int flag;
    UserModel userModel;
    UserModel1 userModel1;

    Favorite_Database favoriteDatabase;

    ArrayList<list_cats> list_cats;

    ArrayList<list_cats.Subs> list_cats_sub;

    String user_id,name,Logo_img;
    String type,order;
    String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();


    }
    private void initview() {

        list_cats =new ArrayList<>();
        final FragmentManager fm = getSupportFragmentManager();
        img_menu = findViewById(R.id.menu_img);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView2 = findViewById(R.id.nav_view2);
        search = findViewById(R.id.txt_search2);
        login_register = findViewById(R.id.tv_login_register);
        View headview=navigationView.getHeaderView(0);
        profile_img = headview.findViewById(R.id.profile_img);
        tv_username = headview.findViewById(R.id.txtname);
        login_register = headview.findViewById(R.id.tv_login_register);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"myorders_db").allowMainThreadQueries().build();
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_SEARCH){
                    final String sanf_name = search.getText().toString();
                    Api.getService().search_Home(sanf_name).enqueue(new Callback<List<HomeModel>>() {
                        @Override
                        public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                            Intent intent = new Intent(MainActivity.this,Search_Activity.class);
                            Log.v("ggg",response.message());
                            intent.putExtra("list",(Serializable)response.body());
                            intent.putExtra("sanf_name",sanf_name);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                            Log.v("jjj",t.getMessage());
                        }
                    });
                    return true;
                }
                return false;
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
            mPrefs =MySharedPreference.getInstance();
            userModel1 = mPrefs.Get_UserData(MainActivity.this);
            if(userModel1!=null){

                 name=userModel1.getFull_name();
                 type=userModel1.getType();
                 Logo_img=userModel1.getLogo_img();
                 order=userModel1.getSend_order();


                Log.d(TAG, "initview: "+type);
                Log.d(TAG, "initview: "+order);


            }

          //  String url = userModel1.getPassword();
//            String url=userModel1.getImage_url();
            tv_username.setText(name);
           // Picasso.with(this).load(url).into(img);
            tv_username.setVisibility(View.VISIBLE);
            profile_img.setVisibility(View.VISIBLE);
            login_register.setVisibility(View.GONE);

            if (Logo_img!=null) {
                Picasso.with(this).load(Logo_img).into(profile_img);
            }else {
                profile_img.setImageResource(R.mipmap.icon_round);
            }

        }catch (Exception e){
            //String personname=i.getStringExtra("personName");
            //img.setImageResource(R.mipmap.icon_round);
            //textView.setText(personname);
        }
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseimage();
            }
        });
        get_list_cats();
        initRecyclerview();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView2 = findViewById(R.id.bottom_navigation2);
        //BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav_listner);
        bottomNavigationView2.setOnNavigationItemSelectedListener(nav_listner2);

        getDataIntent();

        HomeFragment homeFragment=new HomeFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    }

    private void get_list_cats() {


        Api.getService()
                .get_list_cats()
                .enqueue(new Callback<List<list_cats>>() {
                    @Override
                    public void onResponse(Call<List<list_cats>> call, Response<List<list_cats>> response) {

                        if (response.isSuccessful()){

                            list_cats.addAll(response.body());

                            navigationAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<list_cats>> call, Throwable t) {

                    }
                });


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
        navigationAdapter=new NavigationAdapter(list_cats,this);
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
                //case R.id.nav_add:
                //startActivity(new Intent(MainActivity.this,AddProductActivity.class));
                //break;
            }
            return true;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner2 = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedfragment = null;
            switch (item.getItemId()) {
                case R.id.nav_notification:
                    selectedfragment = new NotificationFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    break;
                case R.id.nav_home:
                    selectedfragment = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    break;
                case R.id.nav_favorite:
                    selectedfragment = new Favorite_Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    break;
                case R.id.nav_profile:
                    selectedfragment = new ProfileVendor_Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    break;
                case R.id.nav_add:
                    startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                    break;
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
                myAppDatabase.dao().deleteproduct();
                startActivity(new Intent(MainActivity.this, Login_Activity.class));

                Animatoo.animateInAndOut(MainActivity.this);
                break;
            case R.id.nav_join_us:

                Log.d(TAG, "onNavigationItemSelected: "+type);
                if (type.equals("2")){


                    if (order.equals("0")){

                        startActivity(new Intent(MainActivity.this, Vender_Signup_Activity.class));
                        Animatoo.animateInAndOut(MainActivity.this);
                    }else {

                        Toast.makeText(this, R.string.ordersend, Toast.LENGTH_LONG).show();

                    }

                }else {

                    Toast.makeText(this, R.string.youVonder, Toast.LENGTH_LONG).show();


                }

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
                    shareMessage = shareMessage + "n,,jhhhkhhkkkk";
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

    public void sendHomeItem(String[] image, List<Item> item, String sanf_name, String details, String price, String product_id, String rating, String store_id, String[] colors,String price_before_dis,String like) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", image);
        bundle.putSerializable("itemlist", (Serializable) item);
        bundle.putString("details", details);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("title", sanf_name);
        intent.putExtra("itemlist", (Serializable) item);
        intent.putExtra("details", details);
        intent.putExtra("price", price);
        intent.putExtra("price_before_dis",price_before_dis);
        intent.putExtra("id", product_id);
        intent.putExtra("rate", rating);
        intent.putExtra("store_id",store_id);
        intent.putExtra("like",like);
        Log.v("gggg",store_id);

        if (user_id!=null) {
            intent.putExtra("user_id", Integer.parseInt(user_id));

        }
        intent.putExtra("color",colors);
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

    void getDataIntent() {
//        Intent intent = getIntent();
//        flag = intent.getIntExtra("flag", 0);


        type=userModel1.getType();
        Log.d(TAG, "getDataIntent: "+type);
            if (type.equals("1")) {
                bottomNavigationView2.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.GONE);
            }else {

                bottomNavigationView2.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        }





    public void list_cats_pos(int pos) {

        list_cats list_cats1=list_cats.get(pos);
        list_cats_sub=list_cats1.getSubs();
        int id=Integer.parseInt(list_cats1.getId());

        Intent intent=new Intent(MainActivity.this,Category_Activity.class);
        intent.putExtra("id_main_cats",id);

        Log.d("asmaa", "list_cats_pos: "+id);

        intent.putExtra("cats",list_cats_sub);
        startActivity(intent);


    }

    public void addfavPos(int pos) {

        String sanf_id = homeModels.get(pos).id;

        if (userModel1 != null){
            user_id = userModel1.getId();
    }

        Log.d("Mainasmaaa", "favPos: "+sanf_id);
        Log.d("Mainasmaaa", "favPos: "+user_id);

        final ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Api.getService()
                .add_to_favourite(user_id,sanf_id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {


                        if (response.isSuccessful()){
                            dialog.dismiss();

                            if (response.body().getSuccess() == 1) {

                                Toast.makeText(MainActivity.this, R.string.addfav, Toast.LENGTH_SHORT).show();
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                        dialog.dismiss();

                    }
                });
    }

    public void deletfavPos(int pos) {

        String sanf_id=homeModels.get(pos).id;

        if (userModel1!=null) {
            user_id = userModel1.getId();
        }else {

            Toast.makeText(this, "You Should SignUp First !!", Toast.LENGTH_SHORT).show();
        }

        final ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Api.getService()
                .delet_to_favourite(user_id,sanf_id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {


                        if (response.isSuccessful()){
                            dialog.dismiss();

                            if (response.body().getSuccess() == 1) {

                                Toast.makeText(MainActivity.this, R.string.deletfav, Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                        dialog.dismiss();
                    }
                });
    }

    public void profilePos(HomeModel model) {


        if (profileFragment==null)
        {
            profileFragment = profileFragment.getInstance();
        }

        Log.d("asmaa", "profilePos: "+model);
        Bundle bundle =new Bundle();
        bundle.putString("name",model.storeName);
        bundle.putString("image",model.storeImg);
        bundle.putString("id",model.storeIdFk);
        bundle.putString("type","1");

        profileFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();

    }

}
