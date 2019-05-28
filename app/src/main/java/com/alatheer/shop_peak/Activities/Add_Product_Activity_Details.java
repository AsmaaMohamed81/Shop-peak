package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.alatheer.shop_peak.R;

import net.margaritov.preference.colorpicker.ColorPickerDialog;

import java.io.FileNotFoundException;
import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Add_Product_Activity_Details extends AppCompatActivity {
    Button add_row_Element, btn_element_image, btn_element_color;
    TableLayout t;
    TableRow tr;
    int PICK_IMAGE_REQUEST = 1;
    int DefaultColor;
    Uri filePath;

    ColorPickerDialog pickcolor;
    int color;
    private Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product___details);
        initview();
    }

    private void initview() {
        add_row_Element = findViewById(R.id.btn_add_element);
        t = findViewById(R.id.table);
        t.setColumnStretchable(0, true);
        t.setColumnStretchable(1, true);
        t.setColumnStretchable(2, true);
        DefaultColor = ContextCompat.getColor(this, R.color.colorPrimary);
        add_row_Element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TableLayout tableLayout = (TableLayout) findViewById(R.id.table);

                context = getApplicationContext();


                // Create a new table row.
                final TableRow tableRow = new TableRow(context);

                // Set new table row layout parameters.
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                ImageButton delete2 = new ImageButton(context);
                btn_element_image = new Button(Add_Product_Activity_Details.this);
                btn_element_color = new Button(Add_Product_Activity_Details.this);

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
                        chooseimage();
                    }
                });
                btn_element_color.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        OpenColorPicker(true);


                        color = Color.parseColor("#ffffff");
                        pickcolor = new ColorPickerDialog(Add_Product_Activity_Details.this, color);
                        pickcolor.setAlphaSliderVisible(true);
                        pickcolor.setTitle("PICK");

                        pickcolor.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
                            @Override
                            public void onColorChanged(int color) {


                                btn_element_color.setBackgroundColor(color);
                                btn_element_color.setText("#" + Integer.toHexString(color));
                            }
                        });


                        pickcolor.show();
                    }
                });
            }
        });

    }

    private void OpenColorPicker(boolean b) {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(Add_Product_Activity_Details.this, "color picker closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                DefaultColor = color;
                btn_element_color.setBackgroundColor(color);
                btn_element_color.setText(Integer.toHexString(color));
            }
        });
        ambilWarnaDialog.show();
    }

    private void chooseimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // add_main_image.setVisibility(View.GONE);
                //main_image.setImageBitmap(bitmap);
                //main_image.setVisibility(View.VISIBLE);
                btn_element_image.setText("you selected image");
                Toast.makeText(this, "image added", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "some thing error", Toast.LENGTH_SHORT).show();
        }
    }
}
