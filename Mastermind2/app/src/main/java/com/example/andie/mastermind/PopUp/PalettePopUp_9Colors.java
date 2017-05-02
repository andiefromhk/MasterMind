package com.example.andie.mastermind.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.Fragment.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_9Colors extends  PalettePopUp{

    public PalettePopUp_9Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_9, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color901 = createBtn(pView, btn_handler, R.id.color_901);
        Button btn_color902 = createBtn(pView, btn_handler, R.id.color_902);
        Button btn_color903 = createBtn(pView, btn_handler, R.id.color_903);
        Button btn_color904 = createBtn(pView, btn_handler, R.id.color_904);
        Button btn_color905 = createBtn(pView, btn_handler, R.id.color_905);
        Button btn_color906 = createBtn(pView, btn_handler, R.id.color_906);
        Button btn_color907 = createBtn(pView, btn_handler, R.id.color_907);
        Button btn_color908 = createBtn(pView, btn_handler, R.id.color_908);
        Button btn_color909 = createBtn(pView, btn_handler, R.id.color_909);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_901:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_902:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_903:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_904:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
                case R.id.color_905:
                    paletteBtnEvent(PinColor.color5 ,this);
                    break;
                case R.id.color_906:
                    paletteBtnEvent(PinColor.color6, this);
                    break;
                case R.id.color_907:
                    paletteBtnEvent(PinColor.color7, this);
                    break;
                case R.id.color_908:
                    paletteBtnEvent(PinColor.color8, this);
                    break;
                case R.id.color_909:
                    paletteBtnEvent(PinColor.color9, this);
                    break;
            }
        }};

}
