package com.example.andie.mastermind.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.HintSymbol;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.Model.PinList;
import com.example.andie.mastermind.R;

import java.util.ArrayList;
import java.util.List;

public class AttemptListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ViewHolder viewHolder;
    int num_pin=4;
    ArrayList<PinList> guessAttemptList;
    ArrayList<Hint> hintAttemptList;

    public AttemptListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGameData(int num, ArrayList<PinList> guessList, ArrayList<Hint> hintList) {
        this.num_pin = num;
        this.guessAttemptList = guessList;
        this.hintAttemptList = hintList;
    }

    @Override
    public int getCount() {
        return guessAttemptList.size();
    }

    @Override
    public Object getItem(int pos) {
        return guessAttemptList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return guessAttemptList.get(pos).getId();
    }

    public Hint getHintItem(int pos) {
        return hintAttemptList.get(pos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //update row content here
        if (convertView == null) {
            convertView = createConvertView(parent);
            viewHolder = createViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemIndexTxt.setText(Integer.toString(position+1));

        PinList pList = (PinList) getItem(position);
        List<PinColor> pArrayList = pList.getList();
        Hint hList = getHintItem(position);
        List<HintSymbol> hArrayList = hList.getList();

        for(int i = 0; i<num_pin; i++){
            String drawableName = pArrayList.get(i).getDrawableName();
            viewHolder.guessView[i].setImageResource(getDrawableId(drawableName));
            drawableName = hArrayList.get(i).getDrawableName();
            viewHolder.hintView[i].setImageResource(getDrawableId(drawableName));
        }

        return convertView;
    }

    private int getDrawableId(String drawableName){
        int drawableId;
        Resources res = context.getResources();
        drawableId=res.getIdentifier(drawableName,"drawable",context.getPackageName());
        return drawableId;
    }

    private View createConvertView(ViewGroup parent) {
        View convertView;
        switch (num_pin){
            case 6:
                convertView = layoutInflater.inflate(R.layout.attempt_row_6,parent,false);
                break;
            case 5:
                convertView = layoutInflater.inflate(R.layout.attempt_row_5,parent,false);
                break;
            case 4:
            default:
                convertView = layoutInflater.inflate(R.layout.attempt_row_4,parent,false);
        }
        return convertView;
    }

    private ViewHolder createViewHolder(View view) {
        ViewHolder viewHolder;
        switch (num_pin){
            case 6:
                viewHolder = new ViewHolder_6pins(view);
                break;
            case 5:
                viewHolder = new ViewHolder_5pins(view);
                break;
            case 4:
            default:
                viewHolder = new ViewHolder_4pins(view);
        }
        return viewHolder;
    }

    //inner classes for optimization
    private abstract class ViewHolder {
        TextView itemIndexTxt;
        ImageView[] guessView;
        ImageView[] hintView;

        public ViewHolder(View view) {
            itemIndexTxt = (TextView)view.findViewById(R.id.item_index_txt);
        }
    }

    private class ViewHolder_4pins extends ViewHolder{
        ImageView guess401, guess402, guess403, guess404, hint401, hint402, hint403, hint404;

        public ViewHolder_4pins(View view){
            super(view);
            guess401 = (ImageView) view.findViewById(R.id.guess_row_401);
            guess402 = (ImageView) view.findViewById(R.id.guess_row_402);
            guess403 = (ImageView) view.findViewById(R.id.guess_row_403);
            guess404 = (ImageView) view.findViewById(R.id.guess_row_404);

            hint401 = (ImageView) view.findViewById(R.id.hint_row_401);
            hint402 = (ImageView) view.findViewById(R.id.hint_row_402);
            hint403 = (ImageView) view.findViewById(R.id.hint_row_403);
            hint404 = (ImageView) view.findViewById(R.id.hint_row_404);

            guessView = new ImageView[]{guess401, guess402, guess403, guess404};
            hintView = new ImageView[]{hint401, hint402, hint403, hint404};
        }
    }

    private class ViewHolder_5pins extends ViewHolder{
        ImageView guess501, guess502, guess503, guess504, guess505,
                hint501, hint502, hint503, hint504, hint505;

        public ViewHolder_5pins(View view){
            super(view);
            guess501 = (ImageView) view.findViewById(R.id.guess_row_501);
            guess502 = (ImageView) view.findViewById(R.id.guess_row_502);
            guess503 = (ImageView) view.findViewById(R.id.guess_row_503);
            guess504 = (ImageView) view.findViewById(R.id.guess_row_504);
            guess505 = (ImageView) view.findViewById(R.id.guess_row_505);

            hint501 = (ImageView) view.findViewById(R.id.hint_row_501);
            hint502 = (ImageView) view.findViewById(R.id.hint_row_502);
            hint503 = (ImageView) view.findViewById(R.id.hint_row_503);
            hint504 = (ImageView) view.findViewById(R.id.hint_row_504);
            hint505 = (ImageView) view.findViewById(R.id.hint_row_505);

            guessView = new ImageView[]{guess501, guess502, guess503, guess504, guess505};
            hintView = new ImageView[]{hint501, hint502, hint503, hint504, hint505};
        }
    }

    private class ViewHolder_6pins extends ViewHolder{
        ImageView guess601, guess602, guess603, guess604, guess605, guess606,
                hint601, hint602, hint603, hint604, hint605, hint606;

        public ViewHolder_6pins(View view){
            super(view);
            guess601 = (ImageView) view.findViewById(R.id.guess_row_601);
            guess602 = (ImageView) view.findViewById(R.id.guess_row_602);
            guess603 = (ImageView) view.findViewById(R.id.guess_row_603);
            guess604 = (ImageView) view.findViewById(R.id.guess_row_604);
            guess605 = (ImageView) view.findViewById(R.id.guess_row_605);
            guess606 = (ImageView) view.findViewById(R.id.guess_row_606);

            hint601 = (ImageView) view.findViewById(R.id.hint_row_601);
            hint602 = (ImageView) view.findViewById(R.id.hint_row_602);
            hint603 = (ImageView) view.findViewById(R.id.hint_row_603);
            hint604 = (ImageView) view.findViewById(R.id.hint_row_604);
            hint605 = (ImageView) view.findViewById(R.id.hint_row_605);
            hint606 = (ImageView) view.findViewById(R.id.hint_row_606);

            guessView = new ImageView[]{guess601, guess602, guess603, guess604, guess605, guess606};
            hintView = new ImageView[]{hint601, hint602, hint603, hint604, hint605, hint606};
        }
    }
}

