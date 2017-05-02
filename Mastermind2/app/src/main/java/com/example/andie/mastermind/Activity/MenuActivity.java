package com.example.andie.mastermind.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.R;

public class MenuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btn_start = createBtn(btn_handler, R.id.start_btn);
        Button btn_rank = createBtn(btn_handler, R.id.rank_btn);
        Button btn_set = createBtn(btn_handler, R.id.set_btn);
    }


    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.start_btn:
                    i = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(i);
                    break;
                case R.id.rank_btn:

                    break;
                case R.id.set_btn:
                    i = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };


}
