package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    ImageView close, main_image, mutiple_image1, mutiple_image2, mutiple_image3, mutiple_image4;
    Button add_main_image, Skip, Continue;
     TextView added_post;

    Button delete_item;
    Button btn_element_image;
    EditText product_num1, product_num, product_name, price_after_discount, price_before_discount, element_name, element_description, element_color;
    Button addproduct;
    String name, number, priceafter_discount, pricebefore_discount, elementname, elementdescription, elementcolor;
     int PICK_IMAGE_MULTIPLE = 2 ;
    int PICK_IMAGE_REQUEST = 1;
    ImageButton add, delete;
    int count = 0;
    Uri Image_Uri1, Image_Uri2, Image_Uri3, Image_Uri4, filePath;
     ArrayList<Uri> mArrayUri;
     List<String> imagesEncodedList;
    TableLayout t1, t2;
    TableRow tr1, tr2;
     String imageEncoded;
     ProfileDatabase profileDatabase;
     HomeDatabase homeDatabase;
     MySharedPreference mprefs;
    Button add_row_Element, add_row_Element2;

    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initview();
///////////////////////////////////////


        //////////////////////////////////////////////
    }

    private void initview() {
        close=findViewById(R.id.close);
        main_image = findViewById(R.id.main_image);
        add_row_Element = findViewById(R.id.btn_add_element);
        // add = findViewById(R.id.add_item);
        //delete = findViewById(R.id.delete_item);
        //add_row_Element2 = findViewById(R.id.btn_add_element2);
        added_post=findViewById(R.id.added_post);
        add_main_image = findViewById(R.id.add_main_image);
        product_name = findViewById(R.id.product_name);
        t1 = findViewById(R.id.table);
        //tr1 = findViewById(R.id.table_row1);
        //tr2 = findViewById(R.id.table_row2);
        t1.setColumnStretchable(0, true);
        t1.setColumnStretchable(1, true);
        t1.setColumnStretchable(2, true);
        product_num = findViewById(R.id.product_num);
        price_after_discount = findViewById(R.id.price_after_discount);
        price_before_discount = findViewById(R.id.price_before_discount);
        //element_name = findViewById(R.id.element_name);
        // element_color = findViewById(R.id.element_color);
        // element_description = findViewById(R.id.element_description);
        product_num1 = findViewById(R.id.order_num);
        Continue = findViewById(R.id.btn_continue);
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
        add_row_Element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_row();

            }
        });
        add_main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose_main_image();
                // uploadimage();
            }
        });
        /*add_multiple_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_multiple_images();
            }
        });*/
        added_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        Common.CloseKeyBoard(this, product_name);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Continue.clearAnimation();
                Continue.setAnimation(animation);
                validation();
            }
        });
    }

    private void validation() {
        number = product_num.getText().toString();
        name = product_name.getText().toString();
        pricebefore_discount = price_before_discount.getText().toString();
        priceafter_discount = price_after_discount.getText().toString();
        //elementname = element_name.getText().toString();
        //elementdescription = element_description.getText().toString();
        if (!TextUtils.isEmpty(number) &&
                !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(priceafter_discount) &&
                !TextUtils.isEmpty(pricebefore_discount)
                ) {


            Common.CloseKeyBoard(this, product_num);
            product_num.setError(null);
            product_name.setError(null);
            price_after_discount.setError(null);
            price_before_discount.setError(null);
            // element_name.setError(null);
            //element_description.setError(null);
            Add(number, name, priceafter_discount, pricebefore_discount);

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
        }
    }

    private void Add(String number, String name, String priceafter_discount, String pricebefore_discount) {
        startActivity(new Intent(AddProductActivity.this, Add_Product_Activity_Details.class));

    }

    private void choose_main_image() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void choose_multiple_images() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_MULTIPLE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                add_main_image.setVisibility(View.GONE);
                main_image.setImageBitmap(bitmap);
                main_image.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "some thing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadimage() {

        Image_Uri1 = mArrayUri.get(0);
        Image_Uri2 = mArrayUri.get(1);
        Image_Uri3 = mArrayUri.get(2);
        Image_Uri4 = mArrayUri.get(3);
        mutiple_image1.setImageURI(Image_Uri1);
        mutiple_image2.setImageURI(Image_Uri2);
        mutiple_image3.setImageURI(Image_Uri3);
        mutiple_image4.setImageURI(Image_Uri4);
        //add_multiple_image.setVisibility(View.GONE);
        mutiple_image1.setVisibility(View.VISIBLE);
        mutiple_image2.setVisibility(View.VISIBLE);
        mutiple_image3.setVisibility(View.VISIBLE);
        mutiple_image4.setVisibility(View.VISIBLE);
        String image1 = Image_Uri1.toString();
        String image2 = Image_Uri2.toString();
        int id= Integer.parseInt(product_num.getText().toString());
        UserModel userModel = mprefs.Get_UserData(AddProductActivity.this);
        String name =userModel.getName();

        Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        Log.v("list", String.valueOf(homeDatabase.dao_home().get_profile_data2()));
        startActivity(intent);

    }

    public void Add_row() {

        final TableLayout tableLayout = (TableLayout)findViewById(R.id.table);

        context = getApplicationContext();


        // Create a new table row.
        final TableRow tableRow = new TableRow(context);

        // Set new table row layout parameters.
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(layoutParams);






        EditText sc1=new EditText(context);
        EditText sc2=new EditText(context);

        ImageButton delete2 = new ImageButton(context);



        sc1 = new EditText(AddProductActivity.this);
        sc2 = new EditText(AddProductActivity.this);

        sc1.setBackground(getDrawable(R.drawable.element_txt));
        sc2.setBackground(getDrawable(R.drawable.element_txt));

        delete2.setBackground(getDrawable(R.drawable.ic_close_black_24dp));




        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4f);
        sc1.setLayoutParams(params2);
        sc1.setTextSize(20);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 5f);
        sc2.setLayoutParams(params3);
        sc2.setTextSize(20);
        TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        params4.gravity = Gravity.CENTER;
        delete2.setLayoutParams(params4);




        tableRow.addView(sc1);
        tableRow.addView(sc2);
        tableRow.addView(delete2);

        tableLayout.addView(tableRow);

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout.removeView(tableRow);

            }
        });
    }

    public void delete_row() {
        t1.removeView(tr2);
    }
}
