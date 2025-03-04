package com.alatheer.shop_peak.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.TasnefAdapter;
import com.alatheer.shop_peak.Adapter.cityAdapter;
import com.alatheer.shop_peak.Adapter.governAdapter;
import com.alatheer.shop_peak.Model.Address;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.Tasnefat;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Vender_Signup_Activity extends AppCompatActivity {
    private static final String TAG ="Vender_Signup_Activity" ;
    EditText shop_name, shop_email, address;
    Spinner governate, city;
    Button add_logo, signup, latlon;
    CircleImageView seller_image;
    List<Address> addressList;
    Button add_product;
    List<String> cities;
    int flag;
    private String Name, Email, Governate, City, Address, Category, city_id, govern_id;
    int PICK_IMAGE_REQUEST = 1 ;
    Toolbar toolbar;
    Uri filePath=null;
//////////////////////////FFFF
    private RecyclerView recyc_govern, recyc_city,recyc_tasnefat;
    private LinearLayout container_city, container_govern,container_tasnefat;
    private ExpandableLayout expand_layout_city, expand_layout_govern,expand_layout_tasnefat;

    private ArrayList<Govern> governArrayList;
    private ArrayList<City> cityArrayList;
    private ArrayList<Tasnefat> tasnefatArrayList;

    private cityAdapter cityAdapter;
    private governAdapter governAdapter;
    private TasnefAdapter tasnefAdapter;

    private Govern govern;

    private TextView tv_title_govern, tv_title_city,tv_title_tasnefat;

    private MySharedPreference mySharedPreference;
    private UserModel1 userModel1;

    private String store_tasnef,lat,lang;
    private int IMG=1;
    private final String read_permission = Manifest.permission.READ_EXTERNAL_STORAGE;
    private int MapCode=300;


    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        String lang = Paper.book().read("language");
        if (Paper.book().read("language").equals("ar"))
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        }else
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }

        super.attachBaseContext(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(newBase, lang)));



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender__signup_);
        initview();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent=getIntent();
        flag = intent.getIntExtra("flag",0);

        if (intent!=null){
            lat=intent.getStringExtra("lat");
            lang=intent.getStringExtra("lang");


        }
    }

    private void initview() {
        toolbar = findViewById(R.id.toolbar);
        shop_name = findViewById(R.id.shop_name);
        shop_email = findViewById(R.id.shop_email);
        address = findViewById(R.id.address);
        latlon = findViewById(R.id.addlat_lon);
        add_logo = findViewById(R.id.add_logo);
        signup = findViewById(R.id.btn_sign);
        setSupportActionBar(toolbar);
        seller_image = findViewById(R.id.seller_image);


        /////#FFFFFF/////////////////

        tv_title_govern = findViewById(R.id.tv_title_govern);
        tv_title_city = findViewById(R.id.tv_title_city);

        recyc_govern = findViewById(R.id.recView_govern);
        recyc_city = findViewById(R.id.recView_city);

        container_city = findViewById(R.id.container_city);
        container_govern = findViewById(R.id.container_govern);

        expand_layout_city = findViewById(R.id.expand_layout_city);
        expand_layout_govern = findViewById(R.id.expand_layout_govern);


        container_govern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_govern.toggle(true);

            }
        });

        container_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_city.toggle(true);

            }
        });


        ////////////get Govern/////////////////////////

        get_govern();

        governArrayList = new ArrayList<>();
        cityArrayList = new ArrayList<>();


        recyc_govern.setLayoutManager(new LinearLayoutManager(this));

        governAdapter = new governAdapter(Vender_Signup_Activity.this, governArrayList);

        recyc_govern.setAdapter(governAdapter);

        governAdapter.notifyDataSetChanged();


/////FFFFFFF////////////get Tasnefat////////////////////////////
        recyc_tasnefat=findViewById(R.id.recView_tasnefat);
        container_tasnefat=findViewById(R.id.container_tasnefat);
        expand_layout_tasnefat=findViewById(R.id.expand_layout_tasnefat);
        tv_title_tasnefat=findViewById(R.id.tv_title_tasnefat);

        tasnefatArrayList=new ArrayList<>();


        container_tasnefat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_tasnefat.toggle(true);
            }
        });

        get_Tasnefat();


        recyc_tasnefat.setLayoutManager(new LinearLayoutManager(this));
        tasnefAdapter=new TasnefAdapter(this,tasnefatArrayList);
        recyc_tasnefat.setAdapter(tasnefAdapter);



        ///////////////////////////////////////////////////FFFFFFFF

        mySharedPreference=MySharedPreference.getInstance();



        userModel1=mySharedPreference.Get_UserData(this);

        Log.d(TAG, "initview: "+userModel1.getFull_name());
        shop_name.setText(userModel1.getFull_name());
        shop_email.setText(userModel1.getEmail());
        tv_title_govern.setText(userModel1.getMohafza());
        tv_title_city.setText(userModel1.getMadina());
        address.setText(userModel1.getAddress());


        //////////////////////////////////////////////



        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        Common.CloseKeyBoard(this, shop_name);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.clearAnimation();
                signup.setAnimation(animation);
                validation();
            }
        });
        add_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                
                Check_ReadPermission(IMG);
            }
        });

        seller_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check_ReadPermission(IMG);
            }
        });
        latlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vender_Signup_Activity.this,MapsActivity.class);
                intent.putExtra("flag",0);
                startActivityForResult(intent,MapCode);
            }
        });
        List<Address> addressList = getaddresslist();
        String governate_name1 = addressList.get(0).getGovernate();
        String governate_name2 = addressList.get(1).getGovernate();
        String governate_name3 = addressList.get(2).getGovernate();
        List<String> governate_names = new ArrayList<>();
        governate_names.add(governate_name1);
        governate_names.add(governate_name2);
        governate_names.add(governate_name3);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Vender_Signup_Activity.this, android.R.layout.simple_spinner_item, governate_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        governate.setAdapter(adapter);
//        governate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Vender_Signup_Activity.this, "hello", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    private void Check_ReadPermission(int img) {

        if (ContextCompat.checkSelfPermission(this,read_permission)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{read_permission},img);
        }else
        {
            select_photo(img);
        }

    }

    private void select_photo(int img) {

        Intent intent ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else
        {
            intent = new Intent(Intent.ACTION_GET_CONTENT);

        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        startActivityForResult(intent,img);
    }

    private void get_Tasnefat() {

        Api.getService()
                .getTasnef_Vonder()
                .enqueue(new Callback<List<Tasnefat>>() {
                    @Override
                    public void onResponse(Call<List<Tasnefat>> call, Response<List<Tasnefat>> response) {
                        if (response.isSuccessful()){

                            tasnefatArrayList.addAll(response.body());
                            tasnefAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Tasnefat>> call, Throwable t) {

                    }
                });
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();


                add_logo.setVisibility(View.GONE);
                Picasso.with(this).load(filePath).fit().into(seller_image);
                seller_image.setVisibility(View.VISIBLE);
                Toast.makeText(Vender_Signup_Activity.this, "image added successfully", Toast.LENGTH_SHORT).show();


        }
       else if (requestCode==MapCode&&resultCode==Activity.RESULT_OK)
        {
            lat=data.getStringExtra("lat");
            lang=data.getStringExtra("lang");
        }

    }
    private void validation() {
        String id=userModel1.getId();

        String Full_name = shop_name.getText().toString().trim();
        String Address = address.getText().toString().trim();



        String email = shop_email.getText().toString().trim();


        if (!TextUtils.isEmpty(Full_name)  &&
                !TextUtils.isEmpty(Address)&&
                city_id!=null&&
                govern_id!=null&&
                store_tasnef!=null&&
                lat!=null&&
                lang!=null&&
                filePath!=null&&
                id!=null) {

            shop_name.setError(null);
            shop_email.setError(null);
            address.setError(null);
            tv_title_govern.setError(null);
            tv_title_city.setError(null);
            if(flag != 1) {
                subscribre_vendor(id, Full_name, govern_id, city_id, Address, store_tasnef, lat, lang, filePath);
            }else
                edit_profile(id, Full_name, govern_id, city_id, Address, store_tasnef, lat, lang, filePath);

        } else {
            if (TextUtils.isEmpty(Full_name)) {
                shop_name.setError(getString(R.string.shopname_req));
            } else {
                shop_name.setError(null);
            }

            if (TextUtils.isEmpty(Address)) {
                shop_email.setError(getString(R.string.address_req));
            } else {
                shop_email.setError(null);
            }

            if (govern_id==null) {
                govern_id=userModel1.getGovern();


                if (govern_id==null) {
                    tv_title_govern.setError(getString(R.string.governate_req));
                }
            } else {
                tv_title_govern.setError(null);
            }
            if (city_id==null) {
                city_id=userModel1.getGovern();

                if (city_id==null) {
                    tv_title_city.setError(getString(R.string.city_req));
                }
            } else {
                tv_title_city.setError(null);
            }
            if (store_tasnef==null) {
                tv_title_tasnefat.setError(getString(R.string.tasnef_req));
            } else {
                tv_title_tasnefat.setError(null);
            }

            if (lat==null) {
                latlon.setError(getString(R.string.lat_lang_req));
            } else {
                latlon.setError(null);
            }
            if (lang==null) {
                latlon.setError(getString(R.string.lat_lang_req));
            } else {
                latlon.setError(null);
            }

            if (filePath==null) {
                add_logo.setError(getString(R.string.logo_req));
            } else {
                add_logo.setError(null);
            }


        }

    }

    private void edit_profile(String id, String full_name, String govern_id, String city_id, String address, String store_tasnef, String lat, String lang, Uri filePath) {
        RequestBody Vid = Common.getRequestBodyText(id);
        RequestBody Vfull_name = Common.getRequestBodyText(full_name);
        RequestBody Vmohafza = Common.getRequestBodyText(govern_id);
        RequestBody Vmadina = Common.getRequestBodyText(city_id);
        RequestBody Vaddress = Common.getRequestBodyText(address);
        RequestBody Vstore_tasnef = Common.getRequestBodyText(store_tasnef);
        RequestBody Vlat = Common.getRequestBodyText(lat);
        RequestBody Vlang = Common.getRequestBodyText(lang);
        String type = userModel1.getType();
        RequestBody Vtype = Common.getRequestBodyText(type);
        MultipartBody.Part logo_img = Common.getMultiPart(this,filePath,"logo_img");
        final ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Api.getService().update_user(id,Vfull_name,Vmohafza,Vmadina,Vaddress,Vstore_tasnef,Vlat,Vlang,logo_img,Vtype).enqueue(new Callback<UserModel1>() {
            @Override
            public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess() == 1){
                        dialog.dismiss();
                        UserModel1 userModel=response.body();
                        mySharedPreference.Create_Update_UserData(Vender_Signup_Activity.this,userModel);
                        Log.d("model",mySharedPreference.Get_UserData(Vender_Signup_Activity.this).getFull_name());
                       // user_name.setText(userModel.getFull_name());
                        //Picasso.with(getActivity()).load(userModel.getLogo_img()).into(img_profile);
                        //city.setText(userModel.getCity());
                        Toast.makeText(Vender_Signup_Activity.this, "your profile updated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Vender_Signup_Activity.this,IntroActivity.class));

                        //Intent intent = new Intent(getActivity(), MainActivity.class);
                        //startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel1> call, Throwable t) {

            }
        });

    }

    private void subscribre_vendor(String id, String full_name, String mohafza, String madina, String address, String store_tasnef, String lat, String lang, Uri filePath) {
        ;
        RequestBody Vid = Common.getRequestBodyText(id);
        RequestBody Vfull_name = Common.getRequestBodyText(full_name);
        RequestBody Vmohafza = Common.getRequestBodyText(mohafza);
        RequestBody Vmadina = Common.getRequestBodyText(madina);
        RequestBody Vaddress = Common.getRequestBodyText(address);
        RequestBody Vstore_tasnef = Common.getRequestBodyText(store_tasnef);
        RequestBody Vlat = Common.getRequestBodyText(lat);
        RequestBody Vlang = Common.getRequestBodyText(lang);
        MultipartBody.Part logo_img = Common.getMultiPart(this,filePath,"logo_img");

        final ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


            Api.getService()
                    .subscribre_vendor(Vid,Vfull_name,Vmohafza,Vmadina,Vaddress,Vstore_tasnef,Vlat,Vlang,logo_img)
                    .enqueue(new Callback<UserModel1>() {
                        @Override
                        public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                            if (response.isSuccessful()){
                                dialog.dismiss();


                                if (response.body().getSuccess()==1){

                                  Toast.makeText(Vender_Signup_Activity.this, "تم ارسال طلبك", Toast.LENGTH_SHORT).show();

                                    CreateUserNotSignInAlertDialog(Vender_Signup_Activity.this,getString(R.string.ordersend));

                                    Log.v("response",response.message());

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel1> call, Throwable t) {

                            dialog.dismiss();


                            Toast.makeText(Vender_Signup_Activity.this, "Check Internet", Toast.LENGTH_SHORT).show();
                                Log.v("error",t.getMessage());
                        }
                    });


    }

    public void CreateUserNotSignInAlertDialog(Context context, String msg) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setCancelable(true)
                .create();

        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreference.ClearData(Vender_Signup_Activity.this);
                startActivity(new Intent(Vender_Signup_Activity.this, IntroActivity.class));
                Animatoo.animateInAndOut(Vender_Signup_Activity.this);

            }
        });

        dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(view);
        dialog.show();
    }


    private void Signup(String name, String email, String address, Uri filePath) {
        Intent intent = new Intent(Vender_Signup_Activity.this, MainActivity.class);
        intent.putExtra("flag", 1);
        startActivity(intent);
        Animatoo.animateInAndOut(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        Animatoo.animateInAndOut(this);
    }

    private List<Address> getaddresslist() {
        addressList = new ArrayList<>();
        addressList.add(new Address("Menofia", new String[]{"menouf", "Shebin"}));
        addressList.add(new Address("Elgharbiah", new String[]{"Tanta"}));
        addressList.add(new Address("Elshaqia", new String[]{"Elzagazig"}));
        return addressList;
    }


    private void get_govern() {

        Api.getService()
                .getGovern()
                .enqueue(new Callback<List<Govern>>() {
                    @Override
                    public void onResponse(Call<List<Govern>> call, Response<List<Govern>> response) {

                        if (response.isSuccessful()) {

                            governArrayList.addAll(response.body());
                            governAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Govern>> call, Throwable t) {

                    }
                });


    }

    public void pos_city(int pos) {


        tv_title_city.setText(cityArrayList.get(pos).getName());
        city_id = cityArrayList.get(pos).getId();
        expand_layout_city.toggle(true);

    }

    public void pos_govern(int pos) {


        govern = governArrayList.get(pos);
        govern_id = govern.getId();


        if (govern.getCity().size() > 0) {
            cityArrayList = govern.getCity();

            recyc_city.setLayoutManager(new LinearLayoutManager(this));
            cityAdapter = new cityAdapter(Vender_Signup_Activity.this, cityArrayList);

            recyc_city.setAdapter(cityAdapter);

            tv_title_govern.setText(governArrayList.get(pos).getName());

            expand_layout_govern.toggle(true);

        }
    }

    public void pos_tasnefat(int pos) {

        store_tasnef=tasnefatArrayList.get(pos).getId();

        Toast.makeText(this, ""+store_tasnef, Toast.LENGTH_SHORT).show();
        tv_title_tasnefat.setText(tasnefatArrayList.get(pos).getName());
        expand_layout_tasnefat.toggle(true);


    }
}