package com.example.andie.mastermind.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.R;

public class GameActionBar extends BaseActionBar {

    Button btn_gHome, btn_help, btn_giveUp;

    public GameActionBar(AppCompatActivity activity, View.OnClickListener handler){

        super(activity, R.layout.action_bar_game);

        btn_gHome = createActionBarBtn(activity, handler, R.id.action_bar_home_game);
        btn_help = createActionBarBtn(activity, handler, R.id.action_bar_help_game);
        btn_giveUp = createActionBarBtn(activity, handler, R.id.action_bar_giveup);
    }
}
