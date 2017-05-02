package com.example.andie.mastermind.ActionBar;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class BaseActionBar{

    public BaseActionBar(AppCompatActivity activity, @LayoutRes int resource){

        ViewGroup actionBarLayout = (ViewGroup) activity.getLayoutInflater().inflate(
                resource, null);

        final ActionBar actionBar = activity.getSupportActionBar();
        try {

            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);

        }catch(Exception e){
            Log.e(this.getClass().toString(),e.getMessage());
        }
    }

    public Button createActionBarBtn(Activity activity, View.OnClickListener handler, int id){
        Button btn = (Button) activity.findViewById(id);
        btn.setOnClickListener(handler);
        return btn;
    }
}
