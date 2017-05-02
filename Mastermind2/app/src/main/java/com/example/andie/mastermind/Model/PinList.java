package com.example.andie.mastermind.Model;

import java.util.ArrayList;
import java.util.List;

public class PinList {

    private long id;
    private List<PinColor> pinColorList;

    public PinList(){
        pinColorList =new ArrayList<PinColor>();
    }

    public PinList(PinList pListObj){
        List<PinColor> copyList = new ArrayList<PinColor>(pListObj.getList());
        this.pinColorList = copyList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addPin(int pos,PinColor pinColor){
        pinColorList.add(pos, pinColor);
    }

    public void setPin(int pos, PinColor pinColor){
        pinColorList.set(pos, pinColor);
    }

    public void removePin(int pos){
        pinColorList.remove(pos);
    }

    public PinColor getPinColor(int pos){
        return pinColorList.get(pos);
    }

    public int size(){
        return pinColorList.size();
    }

    public List<PinColor> getList(){
        return this.pinColorList;
    }

    public void setList(List<PinColor> list){
        this.pinColorList = list;
    }

    public boolean isComplete(){
        for (int i = 0; i< pinColorList.size(); i++){
            if (pinColorList.get(i)==PinColor.color0)
                return false;
        }
        return true;
    }

    public boolean allColorMatches(PinList pList){
        for(int i = 0; i< pinColorList.size(); i++){
            if (!pinColorList.get(i).equals(pList.getList().get(i)))
                return false;
        }
        return true;
    }

    public boolean containsColor(PinColor color){
        for(int i = 0; i< pinColorList.size(); i++){
            if (pinColorList.get(i).name().equals(color.name()))
                return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String str;
        str = "Size:"+ pinColorList.size()+" ";
        for (int i = 0; i< pinColorList.size(); i++){
            str+= " Pin0"+ Integer.toString(i+1) + ":" + pinColorList.get(i).name();
        }
        return str;
    }
}
