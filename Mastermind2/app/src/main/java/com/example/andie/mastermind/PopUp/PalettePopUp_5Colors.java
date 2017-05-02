package com.example.andie.mastermind.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.Fragment.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_5Colors extends  PalettePopUp{

    public PalettePopUp_5Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_5, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color501 = createBtn(pView, btn_handler, R.id.color_501);
        Button btn_color502 = createBtn(pView, btn_handler, R.id.color_502);
        Button btn_color503 = createBtn(pView, btn_handler, R.id.color_503);
        Button btn_color504 = createBtn(pView, btn_handler, R.id.color_504);
        Button btn_color505 = createBtn(pView, btn_handler, R.id.color_505);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_501:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_502:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_503:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_504:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
                case R.id.color_505:
                    paletteBtnEvent(PinColor.color5 ,this);
                    break;
            }
        }};

}
