package com.example.andie.mastermind.Model;

import java.util.ArrayList;
import java.util.List;

public class Hint {

    private long id;

    private List<HintSymbol> hintList;

    public Hint(){
        hintList =new ArrayList<HintSymbol>();
    }

    public void addHintSymbol(HintSymbol symbol){
        hintList.add(symbol);
    }

    public void setHintSymbol(int pos, HintSymbol symbol){
        hintList.set(pos, symbol);
    }

    public int size(){
        return hintList.size();
    }

    public List<HintSymbol> getList(){
        return this.hintList;
    }

    public void resetHint(){
        hintList = new ArrayList<HintSymbol>();
    }

    @Override
    public String toString(){
        String str;
        str = "Size:"+ hintList.size()+" ";
        for (int i = 1; i<= hintList.size(); i++){
            str+= " Pin0"+ Integer.toString(i) + ":" + hintList.get(i-1).name();
        }
        return str;
    }
}
