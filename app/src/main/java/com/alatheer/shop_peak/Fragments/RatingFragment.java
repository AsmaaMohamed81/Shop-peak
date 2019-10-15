package com.alatheer.shop_peak.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.IntroActivity;
import com.alatheer.shop_peak.Adapter.RatingAdapter;
import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class RatingFragment extends Fragment {
    RecyclerView ratingrecycler;
    Button btn_add_rate;
    RatingBar ratingBar;
    RecyclerView.LayoutManager layoutManager;
    RatingAdapter ratingAdapter;
    List<RatingModel> ratingModelList;
    String product_id;
    int user_id;
    int stars;
    long id;
    int id2;
    MySharedPreference mprefs;
    UserModel1 userModel1;

    private ProgressBar progressBar;
    private TextView txt_no;

    @Override
    public void onAttach(Context context) {

        Paper.init(context);
        String lang = Paper.book().read("language");

        if (Paper.book().read("language").equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        super.onAttach(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(context, lang)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        ratingrecycler = view.findViewById(R.id.recycler_rating);
        btn_add_rate = view.findViewById(R.id.add_rate);
        ratingBar = view.findViewById(R.id.ratbar);
        mprefs = MySharedPreference.getInstance();
        userModel1 = mprefs.Get_UserData(getActivity());

        if (userModel1!=null) {
            user_id = Integer.parseInt(userModel1.getId());
        }
        progressBar = view.findViewById(R.id.progBar);
        txt_no = view.findViewById(R.id.tv_no);


        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        getdatafromintent();
//        Log.v("usser_id", user_id + "");
//        Log.v("JJJJ",userModel1.getFull_name());
        initrecycler();
        btn_add_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userModel1==null){

                    CreateDialog();
                }else {
                Add_Rate();
                initrecycler();}
            }
        });
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    stars = (int) starsf + 1;
                    ratingBar.setRating(stars);
                    Toast.makeText(getContext(), stars + "", Toast.LENGTH_SHORT).show();
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }
        });

    }

    private void Add_Rate() {

        final ProgressDialog dialog = Common.createProgressDialog(getActivity(),getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Log.v("usser_id", user_id + "");
        initrecycler();
        Api.getService().make_rate(id2, stars, user_id).enqueue(new Callback<RatingModel2>() {
            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                if (response.isSuccessful()){

                    dialog.dismiss();

                    if (response.body().getSuccess()==1) {

                        RatingModel2 ratingModel2 = response.body();
                        initrecycler();

                        Toast.makeText(getActivity(), R.string.addrate, Toast.LENGTH_SHORT).show();
                        Log.v("5555", ratingModel2.success.toString());
                    }
                }


            }

            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {
                dialog.dismiss();

            }
        });
    }

    private void initrecycler() {
        ratingrecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        ratingrecycler.setLayoutManager(layoutManager);
        Api.getService()
                .get_all_rating(id2)
                .enqueue(new Callback<List<RatingModel>>() {
            @Override
            public void onResponse(Call<List<RatingModel>> call, Response<List<RatingModel>> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    if (response.body().size()>0){

                        ratingModelList = response.body();
                        Log.v("ffff", response.message());
                        ratingAdapter = new RatingAdapter(getContext(), ratingModelList);
                        ratingrecycler.setAdapter(ratingAdapter);

                        txt_no.setVisibility(View.GONE);

                    }
                    txt_no.setVisibility(View.VISIBLE);



                }

            }

            @Override
            public void onFailure(Call<List<RatingModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    public void getdatafromintent() {
        product_id = getActivity().getIntent().getStringExtra("id");
        id = Long.parseLong(product_id);
        id2 = Integer.parseInt(product_id);
       // user_id = getActivity().getIntent().getIntExtra("user_id", 0);
    }

    private void CreateDialog() {

        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.SH_Log);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);

            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }
}
