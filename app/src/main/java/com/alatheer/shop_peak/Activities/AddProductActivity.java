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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
     ImageView close,added_image;
     TextView added_post;
    EditText product_num1, product_num, product_name, price_after_discount, price_before_discount, element_name, element_description, element_color;
    Button addproduct;
    String name, number, priceafter_discount, pricebefore_discount, elementname, elementdescription, elementcolor;
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
        product_name = findViewById(R.id.product_name);
        product_num = findViewById(R.id.product_num);
        price_after_discount = findViewById(R.id.price_after_discount);
        price_before_discount = findViewById(R.id.price_before_discount);
        element_name = findViewById(R.id.element_name);
        element_color = findViewById(R.id.element_color);
        element_description = findViewById(R.id.element_description);
        product_num1 = findViewById(R.id.order_num);
        addproduct = findViewById(R.id.btn_add);
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
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        Common.CloseKeyBoard(this, product_name);

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addproduct.clearAnimation();
                addproduct.setAnimation(animation);
                validation();
            }
        });
    }

    private void validation() {
        number = product_num.getText().toString();
        name = product_name.getText().toString();
        pricebefore_discount = price_before_discount.getText().toString();
        priceafter_discount = price_after_discount.getText().toString();
        elementname = element_name.getText().toString();
        elementdescription = element_description.getText().toString();
        elementcolor = element_color.getText().toString();
        if (!TextUtils.isEmpty(number) &&
                !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(priceafter_discount) &&
                !TextUtils.isEmpty(pricebefore_discount) &&
                !TextUtils.isEmpty(elementname) &&
                !TextUtils.isEmpty(elementdescription) &&
                !TextUtils.isEmpty(elementcolor)) {


            Common.CloseKeyBoard(this, product_num);
            product_num.setError(null);
            product_name.setError(null);
            price_after_discount.setError(null);
            price_before_discount.setError(null);
            element_name.setError(null);
            element_description.setError(null);
            element_color.setError(null);
            Add(number, name, priceafter_discount, pricebefore_discount, elementname, elementdescription, elementcolor);

        } else {
            if (TextUtils.isEmpty(number)) {
                product_num.setError(getString(R.string.productnumber_req));
            } else {
                product_num.setError(null);
            }

            if (TextUtils.isEmpty(name)) {
                product_name.setError(getString(R.string.productname_req));
            } else {
                product_name.setError(null);
            }

            if (TextUtils.isEmpty(pricebefore_discount)) {
                price_before_discount.setError(getString(R.string.price_before_discount__req));
            } else {
                price_after_discount.setError(null);
            }
            if (TextUtils.isEmpty(priceafter_discount)) {
                price_after_discount.setError(getString(R.string.price_after_discount_req));
            } else {
                price_after_discount.setError(null);
            }
            if (TextUtils.isEmpty(elementname)) {
                element_name.setError(getString(R.string.element_name_req));
            } else {
                element_name.setError(null);
            }
            if (TextUtils.isEmpty(elementdescription)) {
                element_description.setError(getString(R.string.element_description_req));
            } else {
                element_description.setError(null);
            }
            if (TextUtils.isEmpty(elementcolor)) {
                element_color.setError(getString(R.string.element_color_req));
            } else {
                element_color.setError(null);
            }

        }
    }

    private void Add(String number, String name, String priceafter_discount, String pricebefore_discount, String elementname, String elementdescription, String elementcolor) {
        startActivity(new Intent(AddProductActivity.this, MainActivity.class));

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
        //String title = added_TiTle.getText().toString();
        //String des=added_description.getText().toString();
        //String size=added_size.getText().toString();
        //String price=added_price.getText().toString();
        //String gender=added_gender.getText().toString();
     Image_Uri1 = mArrayUri.get(0);
     Image_Uri2 = mArrayUri.get(1);
     String image1 = Image_Uri1.toString();
     String image2 = Image_Uri2.toString();
     int id= Integer.parseInt(product_num.getText().toString());
     UserModel userModel = mprefs.Get_UserData(AddProductActivity.this);
     String name =userModel.getName();
        // HomeModel homeModel=new HomeModel(image1,image2,title,des,size,price,gender,name,R.drawable.vender_image2);
    // HomeModel homeModel=new HomeModel(id,image,title,"dfkldlfks","50$","XXL","male");
        // homeDatabase.dao_home().addproductItem(homeModel);
     //homeDatabase.dao_home().addproductItem(homeModel);
     Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
     Intent intent=new Intent(this,MainActivity.class);
        Log.v("list", String.valueOf(homeDatabase.dao_home().get_profile_data2()));
     startActivity(intent);

    }
}
