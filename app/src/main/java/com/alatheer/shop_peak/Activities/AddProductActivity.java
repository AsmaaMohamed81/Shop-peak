package com.alatheer.shop_peak.Activities;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.alatheer.shop_peak.Adapter.Main_cats_Adapter;
import com.alatheer.shop_peak.Adapter.Sub_cat_Adapter;
import com.alatheer.shop_peak.Adapter.TasnefAdapter;
import com.alatheer.shop_peak.Adapter.cityAdapter;
import com.alatheer.shop_peak.Adapter.governAdapter;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.Product_Specification;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.Tasnefat;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import net.cachapa.expandablelayout.ExpandableLayout;
import net.margaritov.preference.colorpicker.ColorPickerDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public class AddProductActivity extends AppCompatActivity {
    ImageView close, main_image, mutiple_image1, mutiple_image2, mutiple_image3, mutiple_image4;
    Button add_main_image, Skip, Continue;
    TextView added_post;
    private int IMG=1;
    TableLayout t;
    TableRow tr;
    int DefaultColor;
    String color2;
    ColorPickerDialog pickcolor;
    List<RequestBody>colors;
    List<String>items;
    List<String>descriptions;
    List<MultipartBody.Part>images;
    List<RequestBody>names;
    List<RequestBody>values;
    int color;
    private Context context2 = null;
    EditText product_num1, product_num, product_name, price_after_discount, price_before_discount, element_description;
    Button addproduct;
    String main_id, sub_id;
    String name, number, priceafter_discount, pricebefore_discount, elementname, elementdescription, elementcolor;
    //////////////
    private RecyclerView recyc_main, recyc_sub;
    TextView title_main,title_sub;
    private LinearLayout container_main, container_sub,color_image_layout;
    private ExpandableLayout expand_layout_main, expand_layout_sub;
    /////////////
    private ArrayList<list_cats> main_cats_List;
    private ArrayList<list_cats.Subs> sub_cats_List;
    /////////////
    Main_cats_Adapter main_cats_adapter;
    Sub_cat_Adapter sub_cat_adapter;
    private TasnefAdapter tasnefAdapter;
    RecyclerView.LayoutManager recyc_sub_manager;
    private list_cats main_cat;
    private TextView tv_title_main, tv_title_sub;
    Button add_product;
     int PICK_IMAGE_MULTIPLE = 2 ;
    int PICK_IMAGE_REQUEST = 3;
    ImageButton add, delete;
    String name1,value1;
    int count = 0;
    Uri Image_Uri1, Image_Uri2, Image_Uri3, Image_Uri4, filePath,filePath2;
     ArrayList<Uri> mArrayUri;
     List<Product_Specification> product_specifications;
     List<String> imagesEncodedList;
    TableLayout t1, t2;
    TableRow tr1, tr2;
     String imageEncoded;
     ProfileDatabase profileDatabase;
    //HomeDatabase homeDatabase;
     MySharedPreference mprefs;
    Button add_row_Element, add_row_Element2,btn_element_image, btn_element_color;
    private final String read_permission = Manifest.permission.READ_EXTERNAL_STORAGE;
    private Context context = null;

    EditText sc1,sc2;

     TableLayout tableLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initview();
///////////////////////////////////////


        //////////////////////////////////////////////
    }

    private void initview() {
        recyc_main = findViewById(R.id.recView_main);
        recyc_sub = findViewById(R.id.recView_sub);
        container_main = findViewById(R.id.main_category_container);
        container_sub = findViewById(R.id.sub_category_container);
        expand_layout_main = findViewById(R.id.expand_main_layout);
        expand_layout_sub = findViewById(R.id.expand_sub_layout);
        close=findViewById(R.id.close);
        color_image_layout= findViewById(R.id.color_image_layout);
        main_image = findViewById(R.id.main_image);
        add_product = findViewById(R.id.add_product);
        add_row_Element = findViewById(R.id.btn_add_element);
        add_row_Element2 = findViewById(R.id.btn_add_element2);
        product_specifications = new ArrayList<>();
        // add = findViewById(R.id.add_item);
        //delete = findViewById(R.id.delete_item);
        //add_row_Element2 = findViewById(R.id.btn_add_element2);
        added_post=findViewById(R.id.added_post);
        add_main_image = findViewById(R.id.add_main_image);
        product_name = findViewById(R.id.product_name);
        tv_title_main= findViewById(R.id.tv_title_main);
        tv_title_sub = findViewById(R.id.tv_title_sub);
        t1 = findViewById(R.id.table);
        colors = new ArrayList<>();
        images = new ArrayList<>();
        names = new ArrayList<>();
        values = new ArrayList<>();
        items = new ArrayList<>();
        descriptions = new ArrayList<>();
        //tr1 = findViewById(R.id.table_row1);
        //tr2 = findViewById(R.id.table_row2);
        t1.setColumnStretchable(0, true);
        t1.setColumnStretchable(1, true);
        t1.setColumnStretchable(2, true);
        product_num = findViewById(R.id.product_num);
        price_after_discount = findViewById(R.id.price_after_discount);
        price_before_discount = findViewById(R.id.price_before_discount);
        element_description = findViewById(R.id.item_description);
        //element_name = findViewById(R.id.element_name);
        // element_color = findViewById(R.id.element_color);
        // element_description = findViewById(R.id.element_description);
        product_num1 = findViewById(R.id.order_num);
        Continue = findViewById(R.id.btn_continue);
        Skip = findViewById(R.id.btn_skip);
        mprefs=new MySharedPreference(this);
        // homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
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




                items.add(name1);
                descriptions.add(value1);

                Log.d("asmaaa", "onClick: "+name1);
                Log.d("asmaaa", "onClick: "+value1);




            }
        });
        add_main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_ReadPermission(IMG);
                // uploadimage();
            }
        });
        container_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_main.toggle(true);

            }
        });

        container_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_sub.toggle(true);

            }
        });
        getCategory();
        main_cats_List = new ArrayList<>();
        sub_cats_List = new ArrayList<>();


        recyc_main.setLayoutManager(new LinearLayoutManager(this));

        main_cats_adapter = new Main_cats_Adapter(AddProductActivity.this, main_cats_List);

        main_cats_adapter.notifyDataSetChanged();
        recyc_main.setAdapter(main_cats_adapter);

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

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.clearAnimation();
                Skip.setAnimation(animation);
                validation();
            }
        });
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                name1 =sc1.getText().toString();
//                value1 = sc2.getText().toString();

//                for (int i = 0; i < tableLayout.getChildCount(); i++) {
//
//                    TableRow mRow = (TableRow) tableLayout.getChildAt(i);
//
//
//                    for (int y = 0; y < mRow.getChildCount(); y++) {
//
//
//                        items.add(mRow.getChildAt(y).toString());
//                    }
//                }




                for (int i = 0; i < tableLayout.getChildCount(); i++) {
                    View child = tableLayout.getChildAt(i);

                    if (child instanceof TableRow) {
                        TableRow row = (TableRow) child;




                        for (int x = 0; x < row.getChildCount(); x++) {
                            //View view = row.getChildAt(x);
                            EditText text = (EditText) row.getChildAt(x); // get child index on particular row


                            String title = text.getText().toString();
                            Log.i("Value", title);

                            items.add(title);
                        }
                    }}

                Log.d("item", "onClick: "+items.size());

                color_image_layout.setVisibility(View.VISIBLE);
            }
        });
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_product.clearAnimation();
                add_product.setAnimation(animation);
                validation2();
            }
        });
        t = findViewById(R.id.table2);
        t.setColumnStretchable(0, true);
        t.setColumnStretchable(1, true);
        t.setColumnStretchable(2, true);
        DefaultColor = ContextCompat.getColor(this, R.color.colorPrimary);
        add_row_Element2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TableLayout tableLayout = (TableLayout) findViewById(R.id.table2);

                context = getApplicationContext();


                // Create a new table row.
                final TableRow tableRow = new TableRow(context);

                // Set new table row layout parameters.
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                ImageButton delete2 = new ImageButton(context);
                btn_element_image = new Button(AddProductActivity.this);
                btn_element_color = new Button(AddProductActivity.this);

                btn_element_color.setBackground(getDrawable(R.drawable.element_txt));
                btn_element_image.setBackground(getDrawable(R.drawable.element_txt));

                delete2.setBackground(getDrawable(R.drawable.ic_close_black_24dp));


                TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4.5f);
                btn_element_color.setLayoutParams(params2);
                btn_element_color.setTextSize(20);
                TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4.5f);
                btn_element_image.setLayoutParams(params3);
                btn_element_image.setTextSize(20);
                TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                params4.gravity = Gravity.CENTER;
                delete2.setLayoutParams(params4);


                tableRow.addView(btn_element_color);
                tableRow.addView(btn_element_image);
                tableRow.addView(delete2);

                tableLayout.addView(tableRow);

                delete2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tableLayout.removeView(tableRow);

                    }
                });
                btn_element_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Check_ReadPermission(PICK_IMAGE_REQUEST);
                    }
                });
                btn_element_color.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        OpenColorPicker(true);


                        color = Color.parseColor("#ffffff");
                        pickcolor = new ColorPickerDialog(AddProductActivity.this, color);
                        pickcolor.setAlphaSliderVisible(true);
                        pickcolor.setTitle("PICK");

                        pickcolor.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
                            @Override
                            public void onColorChanged(int color) {


                                btn_element_color.setBackgroundColor(color);
                                btn_element_color.setText("#" + Integer.toHexString(color));
                                color2 = Integer.toHexString(color);
                            }
                        });


                        pickcolor.show();
                    }
                });
            }
        });
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG && resultCode == RESULT_OK
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
        } else if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath2 = data.getData();
            btn_element_image.setText("you selected image");

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);

                 btn_element_image.setText("you selected image");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "some thing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void validation() {
        number = product_num.getText().toString();
        name = product_name.getText().toString();
        pricebefore_discount = price_before_discount.getText().toString();
        priceafter_discount = price_after_discount.getText().toString();
        elementdescription =element_description.getText().toString();
        //elementname = element_name.getText().toString();
        //elementdescription = element_description.getText().toString();
        if (!TextUtils.isEmpty(number) &&
                !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(priceafter_discount) &&
                !TextUtils.isEmpty(pricebefore_discount) &&
                !TextUtils.isEmpty(elementdescription)
                ) {


            Common.CloseKeyBoard(this, product_num);
            product_num.setError(null);
            product_name.setError(null);
            price_after_discount.setError(null);
            price_before_discount.setError(null);
            tv_title_main.setError(null);
            tv_title_sub.setError(null);
            element_description.setError(null);
            AddProduct(number, name,main_id,sub_id,priceafter_discount, pricebefore_discount,elementdescription,filePath);

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
            if (TextUtils.isEmpty(elementdescription)) {
                price_after_discount.setError(getString(R.string.element_description_req));
            } else {
                price_after_discount.setError(null);
            }
           /* if (main_id==null) {
                tv_title_main.setError(getString(R.string.governate_req));
            } else {
                tv_title_main.setError(null);
            }
            if (sub_id == null) {
                tv_title_sub.setError(getString(R.string.city_req));
            } else {
                tv_title_sub.setError(null);
            }*/
        }
    }
    private void validation2() {
        number = product_num.getText().toString();
        name = product_name.getText().toString();
        pricebefore_discount = price_before_discount.getText().toString();
        priceafter_discount = price_after_discount.getText().toString();
        elementdescription =element_description.getText().toString();
        //elementname = element_name.getText().toString();
        //elementdescription = element_description.getText().toString();
        if (!TextUtils.isEmpty(number) &&
                !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(priceafter_discount) &&
                !TextUtils.isEmpty(pricebefore_discount) &&
                !TextUtils.isEmpty(elementdescription) &&
                main_id !=null &&
                sub_id !=null &&
                add_main_image.isClickable()
                ) {


            Common.CloseKeyBoard(this, product_num);
            product_num.setError(null);
            product_name.setError(null);
            price_after_discount.setError(null);
            price_before_discount.setError(null);
            tv_title_main.setError(null);
            tv_title_sub.setError(null);
            element_description.setError(null);
            AddProduct2(number, name,main_id,sub_id,priceafter_discount, pricebefore_discount,elementdescription,filePath,filePath2);

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
            if (TextUtils.isEmpty(elementdescription)) {
                price_after_discount.setError(getString(R.string.element_description_req));
            } else {
                price_after_discount.setError(null);
            }
           /* if (main_id==null) {
                tv_title_main.setError(getString(R.string.governate_req));
            } else {
                tv_title_main.setError(null);
            }
            if (sub_id == null) {
                tv_title_sub.setError(getString(R.string.city_req));
            } else {
                tv_title_sub.setError(null);
            }*/
        }
    }

    private void AddProduct2(String number, String name, String main_id, String sub_id, String price_after_discount, String price_before_discount, String elementdescription, Uri filePath,
            Uri filePath2) {
        UserModel1 userModel = mprefs.Get_UserData(AddProductActivity.this);
        String user_id = userModel.getId();
        RequestBody Vid = Common.getRequestBodyText(user_id);
        RequestBody Vnumber = Common.getRequestBodyText(number);
        RequestBody Vname = Common.getRequestBodyText(name);
        RequestBody Vmain_id = Common.getRequestBodyText(main_id);
        RequestBody Vsub_id = Common.getRequestBodyText(sub_id);
        RequestBody Vprice_after_discount = Common.getRequestBodyText(price_after_discount);
        RequestBody Vprice_before_discount = Common.getRequestBodyText(price_before_discount);
        RequestBody Velementdescription = Common.getRequestBodyText(elementdescription);
        MultipartBody.Part main_image = Common.getMultiPart(this,filePath,"main_img");

        for(String item :items){
            RequestBody Vname1 = Common.getRequestBodyText(item);
            names.add(Vname1);
        }
        for(String description :descriptions){
            RequestBody Vvalue = Common.getRequestBodyText(description);
            values.add(Vvalue);

        }
        RequestBody Vcolor = Common.getRequestBodyText(color2);
        colors.add(Vcolor);
        MultipartBody.Part img = Common.getMultiPart(this,filePath2,"images");
        images.add(img);
        Api.getService()
                .Add_Product2(Vid,Vnumber,Vname,Vmain_id,Vsub_id,Vprice_after_discount,Vprice_before_discount,Velementdescription,
                main_image,names,values,images,colors).enqueue(new Callback<RatingModel2>() {
            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                if(response.isSuccessful()){
                    Log.v("success",response.message());
                    Toast.makeText(AddProductActivity.this, "تم", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {

            }
        });

    }

    private void AddProduct(String number, String name,String main_id,String sub_id, String price_after_discount, String price_before_discount,String elementdescription,Uri filePath) {
        UserModel1 userModel = mprefs.Get_UserData(AddProductActivity.this);
        String user_id = userModel.getId();
        RequestBody Vid = Common.getRequestBodyText(user_id);
        RequestBody Vnumber = Common.getRequestBodyText(number);
        RequestBody Vname = Common.getRequestBodyText(name);
        RequestBody Vmain_id = Common.getRequestBodyText(main_id);
        RequestBody Vsub_id = Common.getRequestBodyText(sub_id);
        RequestBody Vprice_after_discount = Common.getRequestBodyText(price_after_discount);
        RequestBody Vprice_before_discount = Common.getRequestBodyText(price_before_discount);
        RequestBody Velementdescription = Common.getRequestBodyText(elementdescription);
        MultipartBody.Part main_image = Common.getMultiPart(this,filePath,"main_img");
        //RequestBody Vcolor = Common.getRequestBodyText(color2);
        //RequestBody[] Vcolors= new RequestBody[]{Vcolor};
        //MultipartBody.Part[] Vimage= new MultipartBody.Part[]{main_image};
        Api.getService().Add_Product(Vid,Vnumber,Vname,Vmain_id,Vsub_id,Vprice_before_discount,Vprice_after_discount,Velementdescription,main_image).enqueue(new Callback<RatingModel2>() {
            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                if(response.isSuccessful()){
                    Log.v("success",response.message());
                }

            }

            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {

            }
        });
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
        UserModel1 userModel = mprefs.Get_UserData(AddProductActivity.this);
        String name =userModel.getFull_name();

        Toast.makeText(this, "data added successfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        //Log.v("list", String.valueOf(homeDatabase.dao_home().get_profile_data2()));
        startActivity(intent);

    }

    public void Add_row() {

         tableLayout = (TableLayout)findViewById(R.id.table);

        context = getApplicationContext();


        // Create a new table row.
        final TableRow tableRow = new TableRow(context);

        // Set new table row layout parameters.
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(layoutParams);


         sc1=new EditText(context);
         sc2=new EditText(context);

//        ImageButton delete2 = new ImageButton(context);



        sc1 = new EditText(AddProductActivity.this);
        sc2 = new EditText(AddProductActivity.this);

        sc1.setBackground(getDrawable(R.drawable.element_txt));
        sc2.setBackground(getDrawable(R.drawable.element_txt));

//        delete2.setBackground(getDrawable(R.drawable.ic_close_black_24dp));




        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4f);
        sc1.setLayoutParams(params2);
        sc1.setTextSize(20);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 5f);
        sc2.setLayoutParams(params3);
        sc2.setTextSize(20);
        TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        params4.gravity = Gravity.CENTER;
//        delete2.setLayoutParams(params4);




        tableRow.addView(sc1);
        tableRow.addView(sc2);
//        tableRow.addView(delete2);

        tableLayout.addView(tableRow);



        name1 =sc1.getText().toString();
        value1 = sc2.getText().toString();




//        delete2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tableLayout.removeView(tableRow);
//
//            }
//        });
    }

    public void delete_row() {
        t1.removeView(tr2);
    }

     public void pos_main_cat(int pos) {
        main_cat = main_cats_List.get(pos);
        main_id = main_cat.getId();
        if (main_cat.getSubs().size() > 0) {
            sub_cats_List = main_cat.getSubs();

            recyc_sub.setLayoutManager(new LinearLayoutManager(this));
            sub_cat_adapter = new Sub_cat_Adapter(this, sub_cats_List);

            recyc_sub.setAdapter(sub_cat_adapter);

            tv_title_main.setText(main_cats_List.get(pos).getName());

            expand_layout_main.toggle(true);

        }
    }
    public void pos_sub(int pos) {
        tv_title_sub.setText(sub_cats_List.get(pos).getName());
        sub_id = sub_cats_List.get(pos).getId();
        expand_layout_sub.toggle(true);

    }
    private void getCategory() {
        Api.getService()
                .get_list_cats()
                .enqueue(new Callback<List<list_cats>>() {
                    @Override
                    public void onResponse(Call<List<list_cats>> call, Response<List<list_cats>> response) {

                        if (response.isSuccessful()){


                            main_cats_List.addAll(response.body());
                            main_cats_adapter.notifyDataSetChanged();
                            Log.v("kkkkk",response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<list_cats>> call, Throwable t) {
                        Log.v("kkkkk",t.getMessage());
                    }
                });
    }
}
