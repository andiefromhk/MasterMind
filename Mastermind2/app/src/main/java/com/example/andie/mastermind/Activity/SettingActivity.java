package com.example.andie.mastermind.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andie.mastermind.ActionBar.NonGameActionBar;
import com.example.andie.mastermind.R;


public class SettingActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    int setting_num_pin, setting_num_color, setting_num_attempt;
    EditText txtPlayerName;
    RadioGroup radioGpNumPin;
    Spinner spinner_num_color, spinner_num_attempt;
    ArrayAdapter<CharSequence> numColorAdapter, numAttemptAdapter;
    Button btn_save, btn_reset;
    Intent i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initSettingActivity();
        refreshSettingActivity();
    }

    private void initSettingActivity() {

        NonGameActionBar actionBar = new NonGameActionBar(SettingActivity.this, btn_handler_actionbar);

        txtPlayerName = (EditText) findViewById(R.id.setting_player);

        radioGpNumPin = (RadioGroup) findViewById(R.id.setting_num_pin);
        radioGpNumPin.setOnCheckedChangeListener(radioGp_listener);

        btn_save = createBtn(btn_handler, R.id.setting_save);
        btn_reset = createBtn(btn_handler, R.id.setting_reset);

        numColorAdapter = (ArrayAdapter<CharSequence>) createAdapter(this, R.array.num_color_array);
        numAttemptAdapter = (ArrayAdapter<CharSequence>) createAdapter(this, R.array.num_attempt_array);

        spinner_num_color = createSpinner(SettingActivity.this, R.id.setting_num_color, numColorAdapter, this);
        spinner_num_attempt = createSpinner(SettingActivity.this, R.id.setting_num_attempt, numAttemptAdapter, this);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.setting_save:
                    saveSettings();
                    break;
                case R.id.setting_reset:
                    resetSettings();
                    refreshSettingActivity();
                    break;
                default:
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGp_listener = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId) {
                case R.id.setting_num_pin6:
                        setting_num_pin = 6;
                    break;
                case R.id.setting_num_pin5:
                        setting_num_pin = 5;
                    break;
                case R.id.setting_num_pin4:
                default:
                        setting_num_pin = 4;
                    break;
            }
        }
    };

    private void refreshSettingActivity(){
        txtPlayerName.setText(mmc.currentPlayerName());

        int num_pin = mmc.currentSettingNumPin();
        int num_pin_btn_id = get_num_pin_btn_id(num_pin);
        radioGpNumPin.check(num_pin_btn_id);

        int numColorPos = numColorAdapter.getPosition(Integer.toString(mmc.currentSettingNumColor()));
        int numAttemptPos = numAttemptAdapter.getPosition(Integer.toString(mmc.currentAttempt()));

        spinner_num_color.setSelection(numColorPos);
        spinner_num_attempt.setSelection(numAttemptPos);
    }

    private int get_num_pin_btn_id(int num_pin) {
        int num_pin_btn_id;
        switch (num_pin){
            case 6:
                num_pin_btn_id = R.id.setting_num_pin6;
                break;
            case 5:
                num_pin_btn_id = R.id.setting_num_pin5;
                break;
            case 4:
            default:
                num_pin_btn_id = R.id.setting_num_pin4;
        }
        return num_pin_btn_id;
    }

    private void saveSettings() {
        mmc.saveSettingData(txtPlayerName.getText().toString(),
                setting_num_pin, setting_num_color, setting_num_attempt);
        Toast.makeText(getApplicationContext(), "Your setting is saved", Toast.LENGTH_SHORT).show();
    }

    private void resetSettings() {
        mmc.saveSettingData("Player",4,5,12);
        Toast.makeText(getApplicationContext(), "Game setting is reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.setting_num_color)
        {
            switch (pos){
                case 0:
                    setting_num_color = 4;
                    break;
                case 1:
                    setting_num_color = 5;
                    break;
                case 2:
                    setting_num_color = 6;
                    break;
                case 3:
                    setting_num_color = 7;
                    break;
                case 4:
                    setting_num_color = 8;
                    break;
                case 5:
                    setting_num_color = 9;
                    break;
                default:
                    setting_num_color = 5;
            }
        }
        else if(spinner.getId() == R.id.setting_num_attempt)
        {
            switch (pos){
                case 0:
                    setting_num_attempt = 6;
                    break;
                case 1:
                    setting_num_attempt = 12;
                    break;
                case 2:
                    setting_num_attempt = 18;
                    break;
                case 3:
                    setting_num_attempt = 24;
                    break;
                case 4:
                    setting_num_attempt = 30;
                    break;
                default:
                    setting_num_attempt = 12;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){
    }

    View.OnClickListener btn_handler_actionbar = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.action_bar_home:
                    finish();
                    break;

                case R.id.action_bar_record:

                    break;

                default:
            }
        }
    };

}
