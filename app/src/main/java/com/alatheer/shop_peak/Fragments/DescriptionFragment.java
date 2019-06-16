package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.ItemAdapter;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;

import java.util.List;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DescriptionFragment extends Fragment {
    List<Item> items;
    String details;
    RecyclerView followers_recycler;
    RecyclerView.LayoutManager description__layoutManager;
    ItemAdapter itemAdapter;
    TextView txt_details;


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
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        getDataFromIntent();
        followers_recycler = view.findViewById(R.id.recycler_description);
        txt_details=view.findViewById(R.id.text_view);

        initrecycler();

        txt_details.setText(details);


    }

    private void initrecycler() {
        followers_recycler.setHasFixedSize(true);
        description__layoutManager = new LinearLayoutManager(getContext());
        followers_recycler.setLayoutManager(description__layoutManager);
        itemAdapter = new ItemAdapter(items, getContext());
        followers_recycler.setAdapter(itemAdapter);
    }

    public void getDataFromIntent() {
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        items = (List<Item>) extras.getSerializable("itemlist");
        details = getActivity().getIntent().getStringExtra("details");


    }

}
