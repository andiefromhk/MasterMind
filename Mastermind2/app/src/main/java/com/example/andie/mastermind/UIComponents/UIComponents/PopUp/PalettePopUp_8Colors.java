package com.example.andie.mastermind.UIComponents.UIComponents.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_8Colors extends  PalettePopUp{

    public PalettePopUp_8Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_8, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color801 = createBtn(pView, btn_handler, R.id.color_801);
        Button btn_color802 = createBtn(pView, btn_handler, R.id.color_802);
        Button btn_color803 = createBtn(pView, btn_handler, R.id.color_803);
        Button btn_color804 = createBtn(pView, btn_handler, R.id.color_804);
        Button btn_color805 = createBtn(pView, btn_handler, R.id.color_805);
        Button btn_color806 = createBtn(pView, btn_handler, R.id.color_806);
        Button btn_color807 = createBtn(pView, btn_handler, R.id.color_807);
        Button btn_color808 = createBtn(pView, btn_handler, R.id.color_808);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_801:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_802:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_803:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_804:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
                case R.id.color_805:
                    paletteBtnEvent(PinColor.color5 ,this);
                    break;
                case R.id.color_806:
                    paletteBtnEvent(PinColor.color6, this);
                    break;
                case R.id.color_807:
                    paletteBtnEvent(PinColor.color7, this);
                    break;
                case R.id.color_808:
                    paletteBtnEvent(PinColor.color8, this);
                    break;
            }
        }};

}
