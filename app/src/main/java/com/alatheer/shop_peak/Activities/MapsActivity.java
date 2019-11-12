package com.alatheer.shop_peak.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.BasketModel2;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.Pill;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.SendNotify;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapssActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private final int PER_REQ = 12012;
    private boolean isGranted = false;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final float Defult_Zoom = 15f;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;


    private final int gps_req = 102, loc_req = 103;


    private GoogleMap mMap;
    private ImageView gps;
    private Intent intentService = null;
    private EditText edit_address,edit_phone, lat, log;
    private Button btn_continue;
    private Button btn_add_basket;
    MyAppDatabase myAppDatabase;
    String Vlat, Vlang;
    MediaPlayer mSong;
    UserModel1 userModel1;
    long pill_num;
    MySharedPreference mySharedPreference;
    int flag;
    String user_phone;
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
        setContentView(R.layout.activity_maps);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"myorders_db").allowMainThreadQueries().build();
        mySharedPreference = MySharedPreference.getInstance();
        userModel1 = mySharedPreference.Get_UserData(this);
        initView();
        CheckPermission();


    }

    private void initView() {

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String msg = getIntent().getExtras().getString(key);

            }
        }

        gps = findViewById(R.id.img_gps);
        edit_address = findViewById(R.id.address);
        edit_phone = findViewById(R.id.phone);
        lat = findViewById(R.id.lat);
        log = findViewById(R.id.log);
        btn_continue = findViewById(R.id.btn_continue);
        btn_add_basket = findViewById(R.id.btn_add);
        Vlat = lat.getText().toString();
        Vlang = log.getText().toString();
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        final Intent intent = getIntent();
        Api.getService().get_pill().enqueue(new Callback<Pill>() {
            @Override
            public void onResponse(Call<Pill> call, Response<Pill> response) {
                pill_num = response.body().getPillNum();
                Log.v("pill_num",pill_num+"");
            }

            @Override
            public void onFailure(Call<Pill> call, Throwable t) {

            }
        });
        flag = intent.getIntExtra("flag", 0);
        if (flag == 0) {
            btn_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn_continue.clearAnimation();
                    btn_continue.startAnimation(animation);



                    Intent intent = new Intent();
                    intent.putExtra("lat", Vlat);
                    intent.putExtra("lang", Vlang);
                    setResult(RESULT_OK, intent);
                    finish();

                }
            });
        } else {
            edit_phone.setVisibility(View.VISIBLE);
            btn_continue.setVisibility(View.GONE);
            btn_add_basket.setVisibility(View.VISIBLE);
            btn_add_basket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                            String type = intent.getStringExtra("type");
                            String user_id = intent.getStringExtra("user_id");
                            String name = intent.getStringExtra("name");
                            String address = edit_address.getText().toString();
                            List<OrderItemList> list = (List<OrderItemList>) intent.getExtras().getSerializable("list");
                            user_phone = edit_phone.getText().toString();
                            if (!TextUtils.isEmpty(user_phone) && user_phone.length() == 11) {
                                BasketModel2 basketModel2 = new BasketModel2(type, list, user_id, name, address
                                        , Vlat, Vlang, user_phone, pill_num);


                                Save_Basket(basketModel2);

                            }else{
                                if (TextUtils.isEmpty(user_phone)) {
                                    edit_phone.setError(getString(R.string.phone_req));
                                } else {
                                    edit_phone.setError(null);
                                }
                                if (user_phone.length() != 11) {
                                    edit_phone.setError(getString(R.string.phone_error));
                                } else {
                                    edit_phone.setError(null);
                                }

                            }

                        }

                    // long pill_num = intent.getLongExtra("pill_num",0);





            });

        }
    }
    private void Save_Basket(BasketModel2 basketModel2) {
        final ProgressDialog dialog = Common.createProgressDialog(MapsActivity.this, getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Api.getService().add_to_basket(basketModel2).enqueue(new Callback<RatingModel2>() {

            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Api.getService().send_notification().enqueue(new Callback<SendNotify>() {
                        @Override
                        public void onResponse(Call<SendNotify> call, Response<SendNotify> response) {
                            CreateDialog();
                            myAppDatabase.dao().deleteproduct();
                        }

                        @Override
                        public void onFailure(Call<SendNotify> call, Throwable t) {
                            Log.v("error1",t.getMessage());
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {
                Log.v("error2",t.getMessage());
                Toast.makeText(MapsActivity.this, "Check Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
    BroadcastReceiver mhandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            //message.setText(msg);
            mSong.start();
            showNotificationInADialog(msg);
        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        Toast.makeText(this, "Map Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady : Map Ready");

        if (isGpsOpen()) {
            try {
                getDeviceLocation();

                // get image of gps
                mMap.setMyLocationEnabled(true);
                // delete image of gps
                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                init();
            } catch (SecurityException e) {
                e.printStackTrace();
            }


        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                Toast.makeText(MapsActivity.this, "latitude" + latLng.latitude + "," + "longitude" + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });
    }


    private boolean isGpsOpen() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (manager != null && manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        }

        return false;
    }


    private void initMap() {


        Log.d(TAG, "initMap : Init Map ");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getDeviceLocation() {

        Log.d(TAG, "getDeviceLocation: get Currnt Location");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (isGpsOpen()) {
                final Task location = fusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location");

                            Location currentlocation1 = (Location) task.getResult();

                            if (currentlocation1 != null) {

                                Log.d(TAG, "onComplete: " + currentlocation1.getLatitude());
                                Log.d(TAG, "onComplete: " + currentlocation1.getLongitude());
                                moveCamera(new LatLng
                                        (currentlocation1.getLatitude(), currentlocation1.getLongitude()), Defult_Zoom, "myLocation");

//                                String cityName = null;

                                StringBuilder sb = new StringBuilder();

                                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                                List<Address> addresses;
                                try {
                                    addresses = gcd.getFromLocation(currentlocation1.getLatitude(),
                                            currentlocation1.getLongitude(), 1);
                                    if (addresses.size() > 0) {

                                        sb.append(addresses.get(0).getAddressLine(0)).append("\n");
//                                    sb.append(addresses.get(0).getLocality()).append("\n");
//                                    sb.append(addresses.get(0).getPostalCode()).append("\n");
//                                    sb.append(addresses.get(0).getCountryName());

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
//                                String s = currentlocation1.getLongitude() + "\n" + currentlocation1.getLatitude() + "\n\nMy Current City is: "
//                                        + cityName;
                                edit_address.setText(sb);
                                lat.setText(currentlocation1.getLatitude() + " ");
                                log.setText(currentlocation1.getLongitude() + " ");


                            }

                        } else {

                            Log.d(TAG, "onComplete: Not Found");
                            Toast.makeText(MapsActivity.this, "Uenable to get currunt location", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }


        } catch (SecurityException e) {

            Log.d(TAG, "getDeviceLocation: Scyrety exaption" + e.getMessage());

        } catch (NullPointerException e) {
        }


    }


    private void moveCamera(LatLng latLng, float zoom, String title) {


        if (CameraUpdateFactory.newLatLngZoom(latLng, zoom)!=null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        }
        if (!title.equals("myLocation")) {
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);

            mMap.addMarker(options);

        }


    }


    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this, FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = {FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perm, loc_req);
        } else {
            if (isGpsOpen()) {
                initMap();
            } else {
                CreateGpsDialog();
            }
        }
    }

    private void CreateGpsDialog() {

        final AlertDialog gps_dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.app_open_gps);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                OpenGps();
            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }


    private void OpenGps() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, gps_req);


        sendBroadcast(intent);
//
//        String[] permission = new String[]{FINE_LOCATION, COURSE_LOCATION};
//
//        ActivityCompat.requestPermissions(this, permission, PER_REQ);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragmentList) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == gps_req) {
            if (isGpsOpen()) {
//                StartLocationUpdate();
                getDeviceLocation();
                refreshLayout();

            } else {
                CreateGpsDialog();
            }
        }
    }

    private void refreshLayout() {
        Intent intent = getIntent();
        if (intent != null) {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragmentList) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if (requestCode == loc_req) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isGpsOpen()) {
//                        StartLocationUpdate();
                        getDeviceLocation();
                    } else {
                        CreateGpsDialog();
                    }
                } else {
                    Toast.makeText(this, "Access location Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void CreateDialog() {

        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.order_basket_send);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }

    private void showNotificationInADialog(final String message) {

        // show a dialog with the provided title and message
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(userModel1.getType().equals("1")){
                    mSong.stop();
                    Intent intent = new Intent(MapsActivity.this,Notification_Message.class);
                    intent.putExtra("message",message);
                    startActivity(intent);
                }
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
