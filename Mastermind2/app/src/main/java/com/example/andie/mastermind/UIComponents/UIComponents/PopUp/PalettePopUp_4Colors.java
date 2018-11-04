package com.example.andie.mastermind.UIComponents.UIComponents.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_4Colors extends  PalettePopUp{

    public PalettePopUp_4Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_4, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color401 = createBtn(pView, btn_handler, R.id.color_401);
        Button btn_color402 = createBtn(pView, btn_handler, R.id.color_402);
        Button btn_color403 = createBtn(pView, btn_handler, R.id.color_403);
        Button btn_color404 = createBtn(pView, btn_handler, R.id.color_404);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_401:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_402:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_403:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_404:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
            }
        }};

}
