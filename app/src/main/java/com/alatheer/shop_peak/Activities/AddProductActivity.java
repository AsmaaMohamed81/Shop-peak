package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
     ImageView close,added_image;
     TextView added_post;
     EditText added_TiTle,product_num,added_description,added_size,added_price,added_gender;
     int PICK_IMAGE_MULTIPLE = 2 ;
     Uri Image_Uri1,Image_Uri2;
     ArrayList<Uri> mArrayUri;
     List<String> imagesEncodedList;
     String imageEncoded;
     ProfileDatabase profileDatabase;
     HomeDatabase homeDatabase;
     MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initview();
    }

    private void initview() {
        close=findViewById(R.id.close);
        added_image=findViewById(R.id.added_image);
        added_post=findViewById(R.id.added_post);
        added_TiTle=findViewById(R.id.added_title);
        added_description=findViewById(R.id.added_description);
        added_gender=findViewById(R.id.added_gender);
        added_price=findViewById(R.id.added_price);
        added_size=findViewById(R.id.added_size);
        product_num=findViewById(R.id.order_num);
        mprefs=new MySharedPreference(this);
        homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
        profileDatabase = Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,"product_db").allowMainThreadQueries().build();
        //homeDatabase= Room.databaseBuilder(getApplicationContext(),HomeDatabase.class,"home_db").allowMainThreadQueries().build();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProductActivity.this,MainActivity.class));
            }
        });
        added_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseimage();
               // uploadimage();
            }
        });
        added_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
    }

    private void chooseimage() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_MULTIPLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(/*requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE&& */requestCode==PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK) {
            // CropImage.ActivityResult result = CropImage.getActivityResult(data);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            imagesEncodedList = new ArrayList<String>();
            if (data.getData() != null) {

                Uri mImageUri = data.getData();

                // Get the cursor
                Cursor cursor = getContentResolver().query(mImageUri,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imageEncoded = cursor.getString(columnIndex);
                cursor.close();

            } else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                       mArrayUri = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {

                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        final  String path = Environment.getExternalStorageState() + "/"+Environment.DIRECTORY_DCIM + "/";
                        mArrayUri.add(uri);
                        // Get the cursor
                        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded = cursor.getString(columnIndex);
                        imagesEncodedList.add(imageEncoded);
                        cursor.close();
                        Log.v("image_url",path);
                        //Intent intent=new Intent(this,MainActivity.class);
                        //startActivity(intent);
                    }
                    Toast.makeText(this, "selected Images"+mArrayUri.size(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(this, "some thing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadimage() {
     String title = added_TiTle.getText().toString();
     String des=added_description.getText().toString();
     String size=added_size.getText().toString();
     String price=added_price.getText().toString();
     String gender=added_gender.getText().toString();
     Image_Uri1 = mArrayUri.get(0);
     Image_Uri2 = mArrayUri.get(1);
     String image1 = Image_Uri1.toString();
     String image2 = Image_Uri2.toString();
     int id= Integer.parseInt(product_num.getText().toString());
     UserModel userModel = mprefs.Get_UserData(AddProductActivity.this);
     String name =userModel.getName();
     HomeModel homeModel=new HomeModel(image1,image2,title,des,size,price,gender,name,R.drawable.vender_image2);
    // HomeModel homeModel=new HomeModel(id,image,title,"dfkldlfks","50$","XXL","male");
     homeDatabase.dao_home().addproductItem(homeModel);
     //homeDatabase.dao_home().addproductItem(homeModel);
     Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
     Intent intent=new Intent(this,MainActivity.class);
        Log.v("list", String.valueOf(homeDatabase.dao_home().get_profile_data2()));
     startActivity(intent);

    }
}
