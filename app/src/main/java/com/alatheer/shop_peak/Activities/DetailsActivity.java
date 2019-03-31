package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.R;

public class DetailsActivity extends AppCompatActivity {
    ImageView details_img,back_image,add_circle,remove_circle;
    TextView details_title,details_des,counter;
    Button details_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initview();
    }

    private void initview() {
        back_image=findViewById(R.id.back_image);
        details_img=findViewById(R.id.details_image);
        details_title=findViewById(R.id.details_title);
        add_circle=findViewById(R.id.add_circle);
        remove_circle=findViewById(R.id.remove_circle);
        counter=findViewById(R.id.counter);
        //details_des=findViewById(R.id.details_des);
        details_price=findViewById(R.id.details_price);
        Intent intent=getIntent();
        int image=intent.getIntExtra("homeimage",0);
        String title=intent.getStringExtra("title");
        String des=intent.getStringExtra("des");
        String price=intent.getStringExtra("price");
        details_img.setImageResource(image);
        details_title.setText(title);
//        details_des.setText(des);
        details_price.setText(price);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,MainActivity.class));
                finish();
            }
        });
        add_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=Integer.parseInt(counter.getText().toString());
                count++;
                counter.setText(count++ +"");
            }
        });
        remove_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=Integer.parseInt(counter.getText().toString());
                if(count !=0){
                    count--;
                    counter.setText(count-- +"");

                }
            }
        });
    }

}
