package com.alatheer.shop_peak.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alatheer.shop_peak.R;

public class Common {

    public static void CloseKeyBoard(Context context,View view){

        if (context !=null && view !=null){
            InputMethodManager inputMethodManager=(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

            }
        }



    }

    public static Snackbar CreateSnackBar(Context context, View view_id, String msg)
    {
        final Snackbar snackbar = Snackbar.make(view_id,"",Snackbar.LENGTH_INDEFINITE);

        View view = LayoutInflater.from(context).inflate(R.layout.snack_layout,null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        Button btn_undo = view.findViewById(R.id.btn_undo);
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        layout.setPadding(0,0,0,0);
        layout.addView(view);
        return snackbar;

    }




}
