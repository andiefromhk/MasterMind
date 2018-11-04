package com.example.andie.mastermind.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PinColorList {

    private List<PinColor> pinColorList;

    public PinColorList(int length){
        pinColorList = Arrays.asList(new PinColor[length]);
        for(int i=0; i<length; i++){
            pinColorList.set(i,PinColor.color0);
        }
    }

    public List<PinColor> getCopyList(){
        return new ArrayList<>(this.pinColorList);
    }

    public void setPin(int pos, PinColor pinColor){
        this.pinColorList.set(pos, pinColor);
    }

    public PinColor[] getPinArray(){
        return (PinColor[]) this.pinColorList.toArray();
    }

  public boolean isComplete(){
        return !this.pinColorList.contains(PinColor.color0);
    }

    public boolean allColorMatches(PinColorList pArrObj){
        return Arrays.equals(this.pinColorList.toArray(), pArrObj.getPinArray());
    }

    @Override
    public String toString(){
        String str;
        str = "Size:"+ this.pinColorList.size() +" ";
        str += " [ ";
        for (int i = 0; i < this.pinColorList.size(); i++){
            str += this.pinColorList.get(i).name();
            if (i < this.pinColorList.size() - 1)
                str += ", ";
        }
        str += " ] ";
        return str;
    }
}
