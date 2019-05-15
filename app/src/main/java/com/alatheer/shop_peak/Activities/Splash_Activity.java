package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alatheer.shop_peak.Local.MySharedPreference;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Splash_Activity extends AppCompatActivity implements SurfaceHolder.Callback{
    ImageView logo;
    MySharedPreference mPrefs;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
    }

    @Override
    protected void onStart() {
        super.onStart();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        new AsynkTask().execute(holder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    private class AsynkTask extends AsyncTask<SurfaceHolder,Void,Void> {


        @Override
        protected Void doInBackground(SurfaceHolder... voids) {
            try {
                String path = "android.resource://"+getPackageName()+"/"+R.raw.shop;
                mp = MediaPlayer.create(Splash_Activity.this, Uri.parse(path));
                mp.setDisplay(voids[0]);
                mp.setLooping(false);
                //mp.setVolume(0f,0f);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                        mPrefs = new MySharedPreference(Splash_Activity.this);
                        String []data=mPrefs.getDataFromSharedPreference();
                        String name=data[0];
                        String image_url=data[1];
                        Toast.makeText(Splash_Activity.this, "name"+name, Toast.LENGTH_SHORT).show();
                        if(name == null && image_url==null){
                            Intent intent=new Intent(Splash_Activity.this,Login_Activity.class);
                            startActivity(intent);
                            Animatoo.animateDiagonal(Splash_Activity.this);
                            finish();
                        }else {
                            Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
                            intent.putExtra("personName",name);
                            intent.putExtra("image_url",image_url);
                            startActivity(intent);
                            Animatoo.animateDiagonal(Splash_Activity.this);
                            finish();
                        }
                    }
                });
            }catch (IllegalStateException e){}
            catch (Exception e){}

            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
        mp=null;
    }
}

//        try {
//            VideoView videoHolder = new VideoView(this);
//            setContentView(videoHolder);
//            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
//            videoHolder.setVideoURI(video);
//
//            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                public void onCompletion(MediaPlayer mp) {
//                    jump();
//                }
//            });
//            videoHolder.start();
//        } catch (Exception ex) {
//            jump();
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        jump();
//        return true;
//    }
//
//    private void jump() {
//        if (isFinishing())
//            return;
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
//    }
////        initView();
//
//
//    private void initView() {
//
//        logo=findViewById(R.id.logo);
//
//
//         final Animation animation= AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                logo.setVisibility(View.VISIBLE);
//                logo.startAnimation(animation);
//
//            }
//        },200);
//
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mPrefs = new MySharedPreference(Splash_Activity.this);
//                        String []data=mPrefs.getDataFromSharedPreference();
//                        String name=data[0];
//                        String image_url=data[1];
//                        Toast.makeText(Splash_Activity.this, "name"+name, Toast.LENGTH_SHORT).show();
//                        if(name == null && image_url==null){
//                            Intent intent=new Intent(Splash_Activity.this,Login_Activity.class);
//                            startActivity(intent);
//                            finish();
//                        }else {
//                            Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
//                            intent.putExtra("personName",name);
//                            intent.putExtra("image_url",image_url);
//                            startActivity(intent);
//                            finish();
//                        }
//
//
//                    }
//                },300);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
//
//
//}
