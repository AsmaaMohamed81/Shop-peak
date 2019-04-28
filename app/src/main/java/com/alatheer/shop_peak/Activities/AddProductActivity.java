package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Fragments.ProfileFragment;
import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;
import com.theartofdev.edmodo.cropper.CropImage;

public class AddProductActivity extends AppCompatActivity {
     ImageView close,added_image;
     TextView added_post;
     EditText added_TiTle,product_num;
     Uri Image_Uri;
     ProfileDatabase profileDatabase;
     HomeDatabase homeDatabase;
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
        added_TiTle=findViewById(R.id.added_description);
        product_num=findViewById(R.id.order_num);
        profileDatabase = Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,"product_db").allowMainThreadQueries().build();
        homeDatabase= Room.databaseBuilder(getApplicationContext(),HomeDatabase.class,"home_db").allowMainThreadQueries().build();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProductActivity.this,MainActivity.class));
            }
        });
        added_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
        CropImage.activity()
                .setAspectRatio(1,1)
                .start(AddProductActivity.this);

    }
    private String getFileExtention(Uri uri){
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Image_Uri = result.getUri();
            added_image.setImageURI(Image_Uri);
            Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
            //Intent intent=new Intent(this,MainActivity.class);
            //startActivity(intent);

        }
        else {
            Toast.makeText(this, "some thing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadimage() {
     String title = added_TiTle.getText().toString();
     String image = Image_Uri.toString();
     int id= Integer.parseInt(product_num.getText().toString());
     ProfileModel profileModel=new ProfileModel(id,title,image);
     HomeModel homeModel=new HomeModel(id,image,title,"dfkldlfks","50$","XXL","male");
     profileDatabase.dao().addproductItem(profileModel);
     homeDatabase.dao_home().addproductItem(homeModel);
     Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
