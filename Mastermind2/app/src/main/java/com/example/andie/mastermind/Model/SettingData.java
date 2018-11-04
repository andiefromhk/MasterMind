package com.example.andie.mastermind.Model;

import android.content.Context;

public class SettingData extends GameData{
    private static SettingData mmSetting;

    private SettingData(Context context){
        super(context);
        mmSetting = this;
    }

    public static SettingData getInstance(Context context){
        if (mmSetting == null) {
            mmSetting = new SettingData(context);
        }
        return mmSetting;
    }

    public String getPlayerName(){
        return prefs.getString("playerName","MMPlayer");
    }

    public void setPlayerName(String name){
        writePref("playerName",name);
    }

    public int getNumPin(){
        return prefs.getInt("numPins",4);
    }

    public void set_num_pin(int num){
        writePref("numPins", num);
    }

    public int getNumColor(){
        return prefs.getInt("num_color",5);
    }

    public void set_num_color(int num){
        writePref("num_color", num);
    }

    public int getNumAttempt(){
        return prefs.getInt("num_attempt",12);
    }

    public void set_num_attempt(int num){
        writePref("num_attempt", num);
    }
}
