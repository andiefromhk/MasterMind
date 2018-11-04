package com.example.andie.mastermind.UIComponents.UIComponents.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andie.mastermind.R;

public class ColorPickerFragment_5Pins extends ColorPickerFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cView = inflater.inflate(R.layout.player_color_guess_5, container,false);
        return cView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initColorPickerBtn();
        colorPickerEnabled(false);
    }

    private void initColorPickerBtn() {
        btn_guess01 = createColorPickerBtn(btn_handler, R.id.guess_501);
        btn_guess02 = createColorPickerBtn(btn_handler, R.id.guess_502);
        btn_guess03 = createColorPickerBtn(btn_handler, R.id.guess_503);
        btn_guess04 = createColorPickerBtn(btn_handler, R.id.guess_504);
        btn_guess05 = createColorPickerBtn(btn_handler, R.id.guess_505);
    }

    @Override
    public void colorPickerEnabled(boolean enabled){
        btn_guess01.setEnabled(enabled);
        btn_guess02.setEnabled(enabled);
        btn_guess03.setEnabled(enabled);
        btn_guess04.setEnabled(enabled);
        btn_guess05.setEnabled(enabled);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.guess_501:
                    set_gGuessBtnIndex(0);
                    palettePopUp.paletteCtrl(v);
                    break;
                case R.id.guess_502:
                    set_gGuessBtnIndex(1);
                    palettePopUp.paletteCtrl(v);
                    break;
                case R.id.guess_503:
                    set_gGuessBtnIndex(2);
                    palettePopUp.paletteCtrl(v);
                    break;
                case R.id.guess_504:
                    set_gGuessBtnIndex(3);
                    palettePopUp.paletteCtrl(v);
                    break;
                case R.id.guess_505:
                    set_gGuessBtnIndex(4);
                    palettePopUp.paletteCtrl(v);
                    break;
                default:
                    set_gGuessBtnIndex(0);
                    break;
            }
        }
    };

    @Override
    public View.OnClickListener getBtnHandler(){
        return btn_handler;
    }

    @Override
    public void resetColorPickerBtn() {
        btn_guess01.setBackgroundResource(R.drawable.pin00);
        btn_guess02.setBackgroundResource(R.drawable.pin00);
        btn_guess03.setBackgroundResource(R.drawable.pin00);
        btn_guess04.setBackgroundResource(R.drawable.pin00);
        btn_guess05.setBackgroundResource(R.drawable.pin00);
    }

}
