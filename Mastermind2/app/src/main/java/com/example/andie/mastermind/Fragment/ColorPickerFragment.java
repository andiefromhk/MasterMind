package com.example.andie.mastermind.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.andie.mastermind.Controller.MMController;
import com.example.andie.mastermind.PopUp.PalettePopUp;
import com.example.andie.mastermind.PopUp.PalettePopUp_4Colors;
import com.example.andie.mastermind.PopUp.PalettePopUp_5Colors;
import com.example.andie.mastermind.PopUp.PalettePopUp_6Colors;
import com.example.andie.mastermind.PopUp.PalettePopUp_7Colors;
import com.example.andie.mastermind.PopUp.PalettePopUp_8Colors;
import com.example.andie.mastermind.PopUp.PalettePopUp_9Colors;

public abstract class ColorPickerFragment extends Fragment{

    View cView;
    Button btn_guess01, btn_guess02, btn_guess03, btn_guess04, btn_guess05, btn_guess06;
    PalettePopUp palettePopUp;
    MMController mmc;
    int gGuessBtnIndex =0;
    int num_color;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        mmc= MMController.getInstance(getContext());
        num_color = mmc.currentSettingNumColor();
        palettePopUp = createPalettePopupInstance(num_color, this);
        palettePopUp.initColorPaletteBtn(palettePopUp.getView());
    }

    public abstract void colorPickerEnabled(boolean enabled);

    protected Button createColorPickerBtn(View.OnClickListener handler, int id){
        Button btn_guess = (Button) cView.findViewById(id);
        btn_guess.setOnClickListener(handler);
        return btn_guess;
    }

    protected PalettePopUp createPalettePopupInstance(int num_color, ColorPickerFragment pickerFragment){
        PalettePopUp instance;
        switch (num_color){
            case 4:
                instance = new PalettePopUp_4Colors(getContext(),pickerFragment);
                break;
            case 6:
                instance = new PalettePopUp_6Colors(getContext(),pickerFragment);
                break;
            case 7:
                instance = new PalettePopUp_7Colors(getContext(),pickerFragment);
                break;
            case 8:
                instance = new PalettePopUp_8Colors(getContext(),pickerFragment);
                break;
            case 9:
                instance = new PalettePopUp_9Colors(getContext(),pickerFragment);
                break;
            case 5:
            default:
                instance = new PalettePopUp_5Colors(getContext(),pickerFragment);
        }

        return instance;
    }

    public View getView() {
        return cView;
    }

    public int get_gGuessBtnIndex(){
        return gGuessBtnIndex;
    }

    public void set_gGuessBtnIndex(int index){
        this.gGuessBtnIndex = index;
    }

    public abstract View.OnClickListener getBtnHandler();

    public abstract void resetColorPickerBtn();
}
