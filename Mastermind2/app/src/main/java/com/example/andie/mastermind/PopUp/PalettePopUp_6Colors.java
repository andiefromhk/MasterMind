package com.example.andie.mastermind.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.Fragment.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_6Colors extends  PalettePopUp{

    public PalettePopUp_6Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_6, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color601 = createBtn(pView, btn_handler, R.id.color_601);
        Button btn_color602 = createBtn(pView, btn_handler, R.id.color_602);
        Button btn_color603 = createBtn(pView, btn_handler, R.id.color_603);
        Button btn_color604 = createBtn(pView, btn_handler, R.id.color_604);
        Button btn_color605 = createBtn(pView, btn_handler, R.id.color_605);
        Button btn_color606 = createBtn(pView, btn_handler, R.id.color_606);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_601:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_602:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_603:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_604:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
                case R.id.color_605:
                    paletteBtnEvent(PinColor.color5 ,this);
                    break;
                case R.id.color_606:
                    paletteBtnEvent(PinColor.color6, this);
                    break;
            }
        }};

}
