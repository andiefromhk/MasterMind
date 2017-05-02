package com.example.andie.mastermind.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.R;

public class HelpActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button btn_bk = createBtn(btn_handler,R.id.bk_to_game);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.bk_to_game:
                    finish();
                    break;
                default:
            }
        }
    };
}
