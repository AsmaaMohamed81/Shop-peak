package com.alatheer.shop_peak.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alatheer.shop_peak.Local.MySharedPreference;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shaishavgandhi.loginbuttons.FacebookButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity {
    EditText edt_name, edt_password;
    Button Sign_up, log_in;
    SignInButton gmail_login;
    LoginButton facebook_login;
    private String userName, passWord;
    private ProgressDialog dialog;
    private CheckBox checkBox;
    private Boolean accepted = false;
    private View root;
    private Snackbar snackbar;
    MySharedPreference mySharedPreference;
    private  int PICK_IMAGE_REQUEST=1;
    private CallbackManager callbackManager;
    public GoogleSignInOptions gso;
    SharedPreferences preferences;
    GoogleSignInClient googleSignInClient;
    int GmailSignInRequest=0;
    Uri image_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();


    }

    private void initView() {

        checkBox = findViewById(R.id.check_box);
        Sign_up = findViewById(R.id.btn_sign);
        edt_name = findViewById(R.id.user_name);
        edt_password = findViewById(R.id.user_password);
        log_in = findViewById(R.id.btn_login);
        root=findViewById(R.id.root);
        facebook_login=findViewById(R.id.btn_facebook_login);
        //facebook_login.setReadPermissions("email", "public_profile", "user_friends");
        gmail_login=findViewById(R.id.btn_gmail_login);
        gmail_login.setSize(SignInButton.SIZE_STANDARD);
        callbackManager=CallbackManager.Factory.create();
        mySharedPreference = new MySharedPreference(this);
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        facebook_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken=loginResult.getAccessToken();
                GraphRequest request=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String first_name = object.getString("first_name");
                            String last_name=object.getString("last_name");
                            String id=object.getString("id");
                            String image_url="https://graph.facebook.com/"+id+"/picture?type=normal";
                            mySharedPreference.PutDataInSharedPreference(first_name,image_url,"mmmmmm@gmail.com");
                            Intent i=new Intent(Login_Activity.this,MainActivity.class);
                            i.putExtra("personName",first_name);
                            i.putExtra("image_url",image_url);
                            startActivity(i);
                            Toast.makeText(Login_Activity.this, "log in with facebook connected successfully", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle bundle=new Bundle();
                bundle.putString("fields","first_name,last_name,email,id");
                request.setParameters(bundle);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                 Log.e("error",error.toString());
            }
        });
        /*gmail_login.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent signin=googleSignInClient.getSignInIntent();
                startActivityForResult(signin,0);
            }
        });*/

        final Animation animation=AnimationUtils.loadAnimation(this,R.anim.press_anim);
        final Animation animation2=AnimationUtils.loadAnimation(this,R.anim.fade_in);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    accepted = true;
                } else {
                    accepted = false;
                }
            }
        });

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_up.clearAnimation();
                Sign_up.setAnimation(animation2);


                        Intent intent =new Intent(Login_Activity.this, Signup_Activity.class);
                        startActivity(intent);


            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in.clearAnimation();
                log_in.setAnimation(animation);
                validation();

            }
        });
        CreateProgressDialog();
        gmail_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, GmailSignInRequest);
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

                mySharedPreference.PutDataInSharedPreference(personName,personPhoto.toString(),personEmail);
                //Toast.makeText(this,personPhoto.toString(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,MainActivity.class);
                try {
                    String image_url = acct.getPhotoUrl().toString(); //photo_url is String
                     intent.putExtra("personName",personName);
                     intent.putExtra("image_url",image_url);
                     intent.putExtra("personEmail",personEmail);
                    startActivity(intent);
                }catch (Exception e){
                    intent.putExtra("personName",personName);
                    intent.putExtra("personEmail",personEmail);
                    //intent.putExtra("image_url",image_path.toString());

                    startActivity(intent);
                }

                }



            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
           // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

    }

    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){

            }
        }
    };

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

    private void validation() {
        userName = edt_name.getText().toString();
        passWord = edt_password.getText().toString();

        if (!TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(passWord) &&
                accepted) {

            Common.CloseKeyBoard(this, edt_name);
            edt_name.setError(null);
            edt_password.setError(null);
            edt_password.setError(null);
            Login(userName, passWord);


        } else {

            if (TextUtils.isEmpty(userName)){
                edt_name.setError(getString(R.string.name_req));
            }else {
                edt_name.setError(null);
            }

            if (TextUtils.isEmpty(passWord)){
                edt_password.setError(getString(R.string.pass_req));
            }else {
                edt_password.setError(null);
            }


            if (!accepted){

              CreateSnackBar(getString(R.string.accept_terms));

            }


        }
    }

    private void Login(String userName, String passWord) {

        mySharedPreference.PutDataInSharedPreference(userName,passWord,"mmmmm@gmail.com");
        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
        intent.putExtra("personName",userName);
        intent.putExtra("image_url",passWord);
        startActivity(intent);

    }

    public void CreateSnackBar(String msg)
    {
        snackbar = Common.CreateSnackBar(this,root,msg);
        snackbar.show();
    }

    public void dismissSnackBar()
    {
        if (snackbar!=null)
        {
            snackbar.dismiss();

        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        super.onStart();
    }
}
