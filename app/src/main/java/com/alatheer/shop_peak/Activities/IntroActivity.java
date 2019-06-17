package com.alatheer.shop_peak.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {

    SignInButton gmail_login;
    LoginButton facebook_login;
    GoogleSignInClient googleSignInClient;
    Button btn_sign, btn_login;
    int GmailSignInRequest = 0;
    TextView sysa, skip;
    private ProgressDialog dialog;
    MySharedPreference mySharedPreference;
    private CallbackManager callbackManager;
    UserModel1 userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initView();

        mySharedPreference = new MySharedPreference(this);

        String session = mySharedPreference.getSession(this);
        if (!TextUtils.isEmpty(session) || session != null) {
            if (session.equals(Tags.session_login)) {
                UserModel1 userModel1 = mySharedPreference.Get_UserData(this);

                if (userModel1 != null) {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }


    }

    private void initView() {

        gmail_login = findViewById(R.id.btn_gmail_login);
        facebook_login = findViewById(R.id.btn_facebook_login);
        btn_sign = findViewById(R.id.btn_sign);
        btn_login = findViewById(R.id.btn_login);
        sysa = findViewById(R.id.sysa);
        skip = findViewById(R.id.skip);
        CreateProgressDialog();
        skip.setPaintFlags(skip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        facebook_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String id = object.getString("id");
                            String EMAIL = object.getString("Email");
                            String phone = object.getString("phone");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            Api.getService().register(first_name +" " +last_name,EMAIL,phone,"","","","").enqueue(new Callback<UserModel1>() {
                                @Override
                                public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                                    if (response.isSuccessful()) {


                                        dialog.dismiss();

                                        if (response.body().getSuccess() == 1) {


                                            userModel = response.body();

                                            MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                            mySharedPreference.Create_Update_UserData(IntroActivity.this,userModel);

                                            Log.d("model",mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());


                                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }

                                        else if (response.body().getSuccess() == 2){
                                            userModel = response.body();

                                            MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                            mySharedPreference.Create_Update_UserData(IntroActivity.this,userModel);

                                            Log.d("model",mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());


                                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                            startActivity(intent);

                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserModel1> call, Throwable t) {

                                }
                            });
                            Toast.makeText(IntroActivity.this, "log in with facebook connected successfully", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "first_name,last_name,email,id");
                request.setParameters(bundle);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("error", error.toString());

            }
        });
        gmail_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, GmailSignInRequest);
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, Signup_Activity.class);

                startActivity(intent);

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, Login_Activity.class);

                startActivity(intent);

            }
        });
        sysa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://shop-peak.com/Web/about/1"));
                startActivity(intent);

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GmailSignInRequest) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (account != null) {
                String personName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                String personEmail = account.getEmail();
                String personId = account.getId();
                Uri personPhoto = account.getPhotoUrl();
                //SharedPreferences.Editor editor=getSharedPreferences("user_data",MODE_PRIVATE).edit();
                //editor.putString("name",personName);
                //editor.putString("image_url",personPhoto.toString());
                //editor.apply();
                try {
                   // userModel = mySharedPreference.Get_UserData(IntroActivity.this);
                    Api.getService().register(personName,personEmail,"","","","","").enqueue(new Callback<UserModel1>() {
                        @Override
                        public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                            if (response.isSuccessful()) {


                                dialog.dismiss();

                                if (response.body().getSuccess() == 1) {


                                    userModel = response.body();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this,userModel);

                                    Log.d("model",mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());


                                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                else if (response.body().getSuccess() == 2){
                                    userModel = response.body();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this,userModel);

                                    Log.d("model",mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());


                                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<UserModel1> call, Throwable t) {
                             Log.v("error",t.getMessage());
                        }
                    });

                } catch (Exception e) {
                    //userModel = new UserModel(personName, "https://www.wpclipart.com/buildings/shop.png", personEmail);
                }

            }
        } catch (ApiException e) {
            e.printStackTrace();
            //userModel = new UserModel("", "https://www.wpclipart.com/buildings/shop.png", "");
        }
    }
    private void CreateProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.waitt));
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog.setIndeterminateDrawable(drawable);
    }
}