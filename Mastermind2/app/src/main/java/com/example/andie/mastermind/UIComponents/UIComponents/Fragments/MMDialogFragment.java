package com.example.andie.mastermind.UIComponents.UIComponents.Fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andie.mastermind.R;

public class MMDialogFragment extends DialogFragment {
    static MMHandler handler;
    public static MMDialogFragment getNewInstance(String title, String msg,
                                                     MMHandler dialogHandler) {
        handler = dialogHandler;
        MMDialogFragment frag = new MMDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);

        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCancel(DialogInterface dialog){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_normal_2btn, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setTitleMsg(view);
        createBtnOK(view);
        createBtnCancel(view);
    }

    private void setTitleMsg(View view) {
        TextView ansTitle = (TextView) view.findViewById(R.id.dialog_normal_title);
        TextView ansMsg = (TextView) view.findViewById(R.id.dialog_normal_msg);

        String title = getArguments().getString("title");
        ansTitle.setText(title);

        String msg = getArguments().getString("msg");
        ansMsg.setText(msg);
    }

    private void createBtnOK(View view) {
        Button btn_ok = (Button) view.findViewById(R.id.btn_dialog_normal_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();

                final Handler delayHandler = new Handler();
                delayHandler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if (handler != null)
                            handler.clickOk();
                    }
                },200);
            }
        });
    }

    private void createBtnCancel(View view) {
        Button btn_cancel = (Button) view.findViewById(R.id.btn_dialog_normal_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
                if (handler != null)
                    handler.clickCancel();
            }
        });
    }
}
