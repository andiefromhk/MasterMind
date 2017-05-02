package com.example.andie.mastermind.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.R;

public class NonGameActionBar extends BaseActionBar {
    Button btn_home, btn_record, btn_setting;

    public NonGameActionBar(AppCompatActivity activity, View.OnClickListener handler){

        super(activity,R.layout.action_bar_base);

        btn_home= createActionBarBtn(activity, handler, R.id.action_bar_home);
        btn_record= createActionBarBtn(activity, handler, R.id.action_bar_record);
        btn_setting= createActionBarBtn(activity, handler, R.id.action_bar_setting);
    }
}
