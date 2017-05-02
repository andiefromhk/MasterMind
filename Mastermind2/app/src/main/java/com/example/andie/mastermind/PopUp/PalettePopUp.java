package com.example.andie.mastermind.PopUp;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.andie.mastermind.Controller.MMController;
import com.example.andie.mastermind.Fragment.ColorPickerFragment;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;

public abstract class PalettePopUp{

    protected Context context;
    protected ColorPickerFragment delegate;
    protected PopupWindow palettePopUp;
    protected View contentView;
    protected LayoutInflater inflater;
    protected View.OnClickListener btn_handler;
    protected MMController mmc;

    PalettePopUp(Context context, ColorPickerFragment delegate){
        this.context = context;
        this.delegate = delegate;
        palettePopUp = new PopupWindow();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView() {
        return contentView;
    }

    public void showPalette(View anchor) {

        palettePopUp.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        palettePopUp.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);
        palettePopUp.setOutsideTouchable(true);
        palettePopUp.setTouchable(true);
        palettePopUp.setFocusable(true);
        palettePopUp.setContentView(contentView);

        int pos[] = new int[2];

        anchor.getLocationOnScreen(pos);

        Rect anchor_rect = new Rect(pos[0], pos[1], pos[0]
                + anchor.getWidth(), pos[1] + anchor.getHeight());

        contentView.measure(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        int contentViewHeight = contentView.getMeasuredHeight();
        int contentViewWidth = contentView.getMeasuredWidth();

        int pos_x = anchor_rect.centerX() - (contentViewWidth- contentViewWidth / 2);
        int pos_y = anchor_rect.top - contentViewHeight ;

        palettePopUp.showAtLocation(anchor, Gravity.NO_GRAVITY, pos_x,
                pos_y);
    }

    public boolean isShown() {
        if (palettePopUp != null && palettePopUp.isShowing()) {
            return true;
        }
        return false;
    }

    public void dismissPalette() {
        if (palettePopUp != null && palettePopUp.isShowing()) {
            palettePopUp.dismiss();
        }
    }

    public void paletteCtrl(View v){
        if(!isShown()){
            showPalette(v);
        }else dismissPalette();
    }

    public abstract void initColorPaletteBtn(View pView);


    protected void updatePickerButton(PinColor color, Button btn){

        Resources res = context.getResources();
        Drawable bgDrawable;

        String drawableName=color.getDrawableName();
        int drawableId=res.getIdentifier(drawableName,"drawable",context.getPackageName());
        bgDrawable= ResourcesCompat.getDrawable(res,drawableId,null);

        btn.setBackground(bgDrawable);
    }

    protected int getColorPickerBtnId(){

        if (mmc == null){
            mmc = MMController.getInstance(context);
        }

        int id;
        int num_pin = mmc.currentSettingNumPin();
        int index = delegate.get_gGuessBtnIndex();

        switch (num_pin) {
            case 4:
                switch (index) {
                    case 0:
                        id = R.id.guess_401;
                        break;
                    case 1:
                        id = R.id.guess_402;
                        break;
                    case 2:
                        id = R.id.guess_403;
                        break;
                    case 3:
                        id = R.id.guess_404;
                        break;
                    default:
                        id = 0;
                }
                break;
            case 5:
                switch (index) {
                    case 0:
                        id = R.id.guess_501;
                        break;
                    case 1:
                        id = R.id.guess_502;
                        break;
                    case 2:
                        id = R.id.guess_503;
                        break;
                    case 3:
                        id = R.id.guess_504;
                        break;
                    case 4:
                        id = R.id.guess_505;
                        break;
                    default:
                        id = 0;
                }
                break;
            case 6:
                switch (index) {
                    case 0:
                        id = R.id.guess_601;
                        break;
                    case 1:
                        id = R.id.guess_602;
                        break;
                    case 2:
                        id = R.id.guess_603;
                        break;
                    case 3:
                        id = R.id.guess_604;
                        break;
                    case 4:
                        id = R.id.guess_605;
                        break;
                    case 5:
                        id = R.id.guess_606;
                        break;
                    default:
                        id = 0;
                }
                break;
            default:
                id = 0;
        }
        return id;
    }

    protected Button createBtn(View v, View.OnClickListener handler, int id){
        Button btn_guess = (Button) v.findViewById(id);
        btn_guess.setOnClickListener(handler);
        return btn_guess;
    }


    protected void paletteBtnEvent(PinColor color , View.OnClickListener btn_handler) {

        if (mmc == null){
            mmc = MMController.getInstance(context);
        }

        int index = delegate.get_gGuessBtnIndex();

        mmc.update_gGuess(index, color);
        Log.d(getClass().toString(), "gGuessPinSet = "+ mmc.currentGuess().toString());

        Button btnToBeUpdated;
        btnToBeUpdated = createBtn(delegate.getView(), delegate.getBtnHandler(), getColorPickerBtnId());
        updatePickerButton(color, btnToBeUpdated);
        dismissPalette();
    }
}
