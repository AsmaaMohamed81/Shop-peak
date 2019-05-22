package com.alatheer.shop_peak.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Model.LocationModel;
import com.alatheer.shop_peak.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG ="MapssActivity" ;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private final int PER_REQ = 12012;
    private boolean isGranted = false;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final float Defult_Zoom=15f;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;



    private final int gps_req = 102,loc_req=103;


    private GoogleMap mMap;
    private ImageView gps;
    private Intent intentService=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        initView();
        CheckPermission();





    }

    private void initView() {

        gps=findViewById(R.id.img_gps);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Toast.makeText(this, "Map Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onMapReady : Map Ready");

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
                Toast.makeText(MapsActivity.this, "latitude" + latLng.latitude + "," + "longitude" + latLng.longitude, Toast.LENGTH_SHORT).show();
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




    private boolean isGpsOpen()
    {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (manager!=null&&manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            return true;
        }

        return false;
    }



    private void initMap() {

        Log.d(TAG,"initMap : Init Map ");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getDeviceLocation()    {

        Log.d(TAG, "getDeviceLocation: get Currnt Location");

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);

        try {
            if (isGpsOpen()){
                final Task location=fusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location");

                            Location currentlocation1=(Location) task.getResult();

                            if (currentlocation1!=null)
                            {

                                Log.d(TAG, "onComplete: "+currentlocation1.getLatitude());
                                Log.d(TAG, "onComplete: "+currentlocation1.getLongitude());
                                moveCamera(new LatLng
                                        (currentlocation1.getLatitude(),currentlocation1.getLongitude()),Defult_Zoom,"myLocation");

                            }

                        }else {

                            Log.d(TAG, "onComplete: Not Found");
                            Toast.makeText(MapsActivity.this, "Uenable to get currunt location", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }


        }catch (SecurityException e){

            Log.d(TAG, "getDeviceLocation: Scyrety exaption"+e.getMessage());

        }catch (NullPointerException e){}


    }

    private void moveCamera(LatLng latLng,float zoom,String title   ){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));


        if (!title.equals("myLocation")){
            MarkerOptions options=new MarkerOptions()
                    .position(latLng)
                    .title(title);

            mMap.addMarker(options);

        }


    }



    private void CheckPermission()
    {
        if (ContextCompat.checkSelfPermission(this,FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            String [] perm = {FINE_LOCATION};
            ActivityCompat.requestPermissions(this,perm,loc_req);
        }else
        {
            if (isGpsOpen())
            {
                initMap();
            }else
            {
                CreateGpsDialog();
            }
        }
    }

    private void CreateGpsDialog() {

        final AlertDialog gps_dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog,null);
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

        gps_dialog.getWindow().getAttributes().windowAnimations=R.style.custom_dialog;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }


    private void OpenGps()
    {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent,gps_req);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment:fragmentList)
        {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode==gps_req)
        {
            if (isGpsOpen())
            {
//                StartLocationUpdate();
            }else
            {
                CreateGpsDialog();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment:fragmentList)
        {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if (requestCode==loc_req)
        {
            if (grantResults.length>0)
            {
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if (isGpsOpen())
                    {
//                        StartLocationUpdate();
                    }else
                    {
                        CreateGpsDialog();
                    }
                }else
                {
                    Toast.makeText(this, "Access location Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
