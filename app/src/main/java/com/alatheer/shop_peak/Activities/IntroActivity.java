package com.alatheer.shop_peak.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.RequestBody;
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
    AlertDialog alertDialog, alertDialog2;

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
        //GOOGLE PLAY SIGNING SHA-1 KEY: 52:49:F9:6B:44:89:BC:A6:DA:8F:AC:B9:1F:FD:1E:DD:98:81:E7:69
        byte[] sha1 = {0x52, 0x49, (byte) 0xF9, 0x6B, 0x44, (byte) 0x89, (byte) 0xBC, (byte) 0xA6, (byte) 0xDA, (byte) 0x8F, (byte) 0xAC, (byte) 0xB9, 0x1F, (byte) 0xFD
                , 0X1E, (byte) 0xDD, (byte) 0x98, (byte) 0x81, (byte) 0xE7, 0X69};
        Log.v("keyhashGOOGLESIGNIN", Base64.encodeToString(sha1, Base64.NO_WRAP));
        CreateProgressDialog();
        CreateAlertDialog();
        CreateAlertDialog2();
        skip.setPaintFlags(skip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        facebook_login.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
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
                            String email = object.getString("email");
                            Log.v("email", email);
                            //  String phone = object.getString("phone");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            Uri image_file = Uri.parse(image_url);
                            //RequestBody Vfirst_name = Common.getRequestBodyText(first_name);
                            RequestBody VEmail = Common.getRequestBodyText("");
                            Api.getService().register2(first_name, email, "", "", "", "", "", image_url).enqueue(new Callback<UserModel1>() {
                                @Override
                                public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                                    if (response.isSuccessful()) {


                                        dialog.dismiss();

                                        if (response.body().getSuccess() == 1) {


                                            userModel = response.body();

                                            MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                            mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                            Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());

                                            alertDialog.show();
                                            //Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                            //startActivity(intent);
                                        } else if (response.body().getSuccess() == 2) {
                                            userModel = response.body();

                                            MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                            mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                            Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());


                                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            alertDialog2.show();

                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserModel1> call, Throwable t) {
                                    Log.e("error1", t.getMessage());
                                    Toast.makeText(IntroActivity.this, "error " + t.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

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
                Toast.makeText(IntroActivity.this, "error2 " + error.getMessage(), Toast.LENGTH_SHORT).show();

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
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GmailSignInRequest) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Toast.makeText(IntroActivity.this, "handle result ", Toast.LENGTH_SHORT).show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

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
                RequestBody Vpersonname = Common.getRequestBodyText(personName);
                RequestBody VEmail = Common.getRequestBodyText(personEmail);
                RequestBody Vphone = Common.getRequestBodyText("");
                RequestBody Vmohafza = Common.getRequestBodyText("");
                RequestBody Vmadina = Common.getRequestBodyText("");
                RequestBody Vaddress = Common.getRequestBodyText("");


                //MultipartBody.Part logo_img = Common.getMultiPart(this,personPhoto,"logo_img");
                //SharedPreferences.Editor editor=getSharedPreferences("user_data",MODE_PRIVATE).edit();
                //editor.putString("name",personName);
                //editor.putString("image_url",personPhoto.toString());
                //editor.apply();
                try {
                    String filepath = personPhoto.toString();
                    Log.e("filepath", filepath);
                    // userModel = mySharedPreference.Get_UserData(IntroActivity.this);
                    Api.getService().register2(personName, personEmail, "", "", "", "", "", filepath).enqueue(new Callback<UserModel1>() {
                        @Override
                        public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                            if (response.isSuccessful()) {


                                dialog.dismiss();

                                if (response.body().getSuccess() == 1) {


                                    userModel = response.body();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                    Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());

                                    alertDialog.show();
                                    //Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    //startActivity(intent);
                                } else if (response.body().getSuccess() == 2) {
                                    userModel = response.body();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                    Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());

                                    alertDialog2.show();
                                    //Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    //startActivity(intent);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel1> call, Throwable t) {
                            Log.v("error", t.getMessage());
                            Toast.makeText(IntroActivity.this, "error1 " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (Exception e) {
                    Api.getService().register(personName, personEmail, "", "", "", "", "").enqueue(new Callback<UserModel1>() {
                        @Override
                        public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                            if (response.isSuccessful()) {


                                dialog.dismiss();

                                if (response.body().getSuccess() == 1) {


                                    userModel = response.body();
                                    update_Token();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                    Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());

                                    alertDialog.show();
                                    //Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    //startActivity(intent);
                                } else if (response.body().getSuccess() == 2) {
                                    userModel = response.body();

                                    MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                    mySharedPreference.Create_Update_UserData(IntroActivity.this, userModel);

                                    Log.d("model", mySharedPreference.Get_UserData(IntroActivity.this).getFull_name());

                                    alertDialog2.show();
                                    //Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                    //startActivity(intent);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel1> call, Throwable t) {
                            Log.v("error", t.getMessage());
                            Toast.makeText(IntroActivity.this, "error1 " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //userModel = new UserModel(personName, "https://www.wpclipart.com/buildings/shop.png", personEmail);

                }

            }
        } catch (ApiException e) {
            e.printStackTrace();
            //userModel = new UserModel("", "https://www.wpclipart.com/buildings/shop.png", "");
            Toast.makeText(IntroActivity.this, "api error" + e.getMessage(), Toast.LENGTH_LONG).show();

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

    private void CreateAlertDialog() {
        alertDialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(R.string.success_register)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).create();

        alertDialog.setCanceledOnTouchOutside(false);


    }

    private void CreateAlertDialog2() {
        alertDialog2 = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(R.string.success_login)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        intent.putExtra("flag", 0);
                        startActivity(intent);
                    }
                }).create();

        alertDialog2.setCanceledOnTouchOutside(false);


    }

    private void update_Token() {
        if (userModel != null) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(Task<InstanceIdResult> task) {
                            if (task.isSuccessful()) {
                                String fireBaseToken = task.getResult().getToken();
                                Log.d("Token", "onComplete: " + fireBaseToken);
                                String user_id = userModel.getId();
                                Log.d("id", "onComplete: " + user_id);

                                Api.getService()
                                        .update_Token(fireBaseToken, user_id)
                                        .enqueue(new Callback<RatingModel2>() {
                                            @Override
                                            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                                                if (response.isSuccessful()) {
                                                    if (response.body().getSuccess() == 1) {
                                                        Log.e("user_token_update", "success");
                                                    }


                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<RatingModel2> call, Throwable t) {

                                                try {
                                                    Log.e("Error", t.getMessage());
                                                } catch (Exception e) {
                                                }
                                            }
                                        });
                            }
                        }
                    });
        }
    }
}