package com.example.andie.mastermind.UIComponents.UIComponents.Fragments;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.andie.mastermind.Model.PinColorList;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class MMAnsDialogFragment extends DialogFragment {
    static MMHandler handler;
    static PinColorList ans;
    Intent i;

    public static MMAnsDialogFragment getNewInstance(PinColorList ansArr, String title, String msg,
                                                     MMHandler dialogHandler) {
        handler = dialogHandler;
        ans = ansArr;
        MMAnsDialogFragment frag = new MMAnsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);

        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_show_ans, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setTitleMsg(view);
        generateAnsView(view);
        createBtnOK(view);
    }

    private void setTitleMsg(View view) {
        TextView ansTitle = (TextView) view.findViewById(R.id.dialog_ans_title);
        TextView ansMsg = (TextView) view.findViewById(R.id.dialog_ans_msg);

        String title = getArguments().getString("title");
        ansTitle.setText(title);

        String msg = getArguments().getString("msg");
        ansMsg.setText(msg);
    }

    private void generateAnsView(View view) {
        LinearLayout ansListView = (LinearLayout) view.findViewById(R.id.pinlist_ans);
        PinColor[] ansArr = ans.getPinArray();

        for (int i = 0; i<ansArr.length; i++){
            ImageView pinImage = new ImageView(getContext());
            String drawableName = ansArr[i].getDrawableName();
            pinImage.setImageResource(getDrawableId(drawableName));
            int pinSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pinSize, pinSize);
            pinImage.setLayoutParams(params);
            ansListView.addView(pinImage);
        }
    }

    private int getDrawableId(String drawableName){
        int drawableId;
        Resources res = getContext().getResources();
        drawableId=res.getIdentifier(drawableName,"drawable",getContext().getPackageName());
        return drawableId;
    }

    private void createBtnOK(View view) {
        Button btn_ok = (Button) view.findViewById(R.id.btn_dialog_ans_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();

                if (handler != null)
                    handler.clickOk();

            }
        });
    }
}
