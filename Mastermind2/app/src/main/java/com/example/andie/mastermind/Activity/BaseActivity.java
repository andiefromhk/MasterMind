package com.example.andie.mastermind.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.andie.mastermind.R;


public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public Button createBtn(View.OnClickListener handler, int id){
        Button btn = (Button) findViewById(id);
        btn.setOnClickListener(handler);
        return btn;
    }

    public Adapter createAdapter(Context context, int arrayId){
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(context,
                arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.setting_spinner_dropdown_item);
        return adapter;
    }
    public Spinner createSpinner(Activity activity, int viewId, ArrayAdapter adapter, AdapterView.OnItemSelectedListener listener){
        Spinner spinner = (Spinner) activity.findViewById(viewId);
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
        return spinner;
    }
}
