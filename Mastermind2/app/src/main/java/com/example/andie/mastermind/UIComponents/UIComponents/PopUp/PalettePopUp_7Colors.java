package com.example.andie.mastermind.UIComponents.UIComponents.PopUp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public class PalettePopUp_7Colors extends  PalettePopUp{

    public PalettePopUp_7Colors(Context context, ColorPickerFragment delegate) {
        super(context, delegate);
        contentView = inflater.inflate(R.layout.color_palette_7, null);
        initColorPaletteBtn(contentView);
    }

    @Override
    public void initColorPaletteBtn(View pView){

        Button btn_color701 = createBtn(pView, btn_handler, R.id.color_701);
        Button btn_color702 = createBtn(pView, btn_handler, R.id.color_702);
        Button btn_color703 = createBtn(pView, btn_handler, R.id.color_703);
        Button btn_color704 = createBtn(pView, btn_handler, R.id.color_704);
        Button btn_color705 = createBtn(pView, btn_handler, R.id.color_705);
        Button btn_color706 = createBtn(pView, btn_handler, R.id.color_706);
        Button btn_color707 = createBtn(pView, btn_handler, R.id.color_707);
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.color_701:
                    paletteBtnEvent(PinColor.color1, this);
                    break;
                case R.id.color_702:
                    paletteBtnEvent(PinColor.color2, this);
                    break;
                case R.id.color_703:
                    paletteBtnEvent(PinColor.color3, this);
                    break;
                case R.id.color_704:
                    paletteBtnEvent(PinColor.color4, this);
                    break;
                case R.id.color_705:
                    paletteBtnEvent(PinColor.color5 ,this);
                    break;
                case R.id.color_706:
                    paletteBtnEvent(PinColor.color6, this);
                    break;
                case R.id.color_707:
                    paletteBtnEvent(PinColor.color7, this);
                    break;
            }
        }};

}
