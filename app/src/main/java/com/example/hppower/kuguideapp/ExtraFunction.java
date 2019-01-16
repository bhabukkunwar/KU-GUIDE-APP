package com.example.hppower.kuguideapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ExtraFunction {

    Activity activity;
    Context context;

    public int color = Color.RED, id;
    private boolean connected = false;
    Snackbar snackbar;


    public ExtraFunction(Activity mActivity, Context mContext){

        activity = mActivity;
        context = mContext;

    }


    public boolean checkInternet(String snackAct) {
        Log.e("apkflow", "checkInternet");

        if (snackAct.equals("activity_mainPage"))
            id =R.id.activity_main;



        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        } catch (Exception e) {
            Log.e("apkflow", e.getMessage());
        }

        if (!connected) {
            Log.e("apkflow", "No internet connection");

            snackbar = Snackbar.make(activity.findViewById(id), "No Internet Connection", 3500);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.setAction("DISMISS", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        } else {
            Log.e("apkflow","Connected to Internet");
        }

        return  connected;
    }






}
