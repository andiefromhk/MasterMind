package com.example.andie.mastermind.UIComponents.UIComponents.PopUp;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.andie.mastermind.Manager.GameManager;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.R;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment;

import java.util.HashMap;
import java.util.Map;

public abstract class PalettePopUp{

    protected Context context;
    protected ColorPickerFragment delegate;
    protected PopupWindow palettePopUp;
    protected View contentView;
    protected LayoutInflater inflater;
    protected View.OnClickListener btn_handler;
    protected GameManager gm;

    private static final Map<Integer,Integer[]> colorPickerBtnIds;
    static
    {
        colorPickerBtnIds = new HashMap<Integer,Integer[]>();
        Integer[] colorPicker_4 = {R.id.guess_401, R.id.guess_402, R.id.guess_403, R.id.guess_404};
        Integer[] colorPicker_5 = {R.id.guess_501, R.id.guess_502, R.id.guess_503, R.id.guess_504, R.id.guess_505};
        Integer[] colorPicker_6 = {R.id.guess_601, R.id.guess_602, R.id.guess_603, R.id.guess_604, R.id.guess_605, R.id.guess_606};
        colorPickerBtnIds.put(4, colorPicker_4);
        colorPickerBtnIds.put(5, colorPicker_5);
        colorPickerBtnIds.put(6, colorPicker_6);
    }

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

        if (gm == null){
            gm = GameManager.getInstance();
        }

        int num_pin = gm.getNumPin();
        int index = delegate.get_gGuessBtnIndex();

        int id = colorPickerBtnIds.get(num_pin)[index];
        return id;
    }

    protected Button createBtn(View v, View.OnClickListener handler, int id){
        Button btn_guess = (Button) v.findViewById(id);
        btn_guess.setOnClickListener(handler);
        return btn_guess;
    }


    protected void paletteBtnEvent(PinColor color , View.OnClickListener btn_handler) {

        if (gm == null){
            gm = GameManager.getInstance();
        }

        int index = delegate.get_gGuessBtnIndex();

        gm.updateCurrentGuess(index, color);

        Button btnToBeUpdated;
        btnToBeUpdated = createBtn(delegate.getView(), delegate.getBtnHandler(), getColorPickerBtnId());
        updatePickerButton(color, btnToBeUpdated);
        dismissPalette();
    }
}
