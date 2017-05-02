package com.example.andie.mastermind.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class MMSettingData {
    private static MMSettingData mmSetting;
    private final Context context;
    private final SharedPreferences.Editor editor;

    private String playerName;
    private int num_pin, num_color, num_attempt;

    private MMSettingData(Context context){
        mmSetting = this;
        this.context = context;
        SharedPreferences prefs = this.context.getApplicationContext().getSharedPreferences(
                "andie-mm", Context.MODE_PRIVATE);

        editor = prefs.edit();

        playerName = prefs.getString("playerName","Player");
        num_pin = prefs.getInt("numPins",4);
        num_color = prefs.getInt("num_color",5);
        num_attempt = prefs.getInt("num_attempt",12);
    }

    public static synchronized MMSettingData getInstance(Context context){
        if (mmSetting == null) {
            mmSetting = new MMSettingData(context);
        }
        return mmSetting;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String name){
        this.playerName = name;
        writePref("playerName",name);
    }

    public int get_num_pin(){
        return num_pin;
    }

    public void set_num_pin(int num){
        this.num_pin = num;
        writePref("numPins", num);
    }

    public int get_num_color(){
        return num_color;
    }

    public void set_num_color(int num){
        this.num_color=num;
        writePref("num_color", num);
    }

    public int get_num_attempt(){
        return num_attempt;
    }

    public void set_num_attempt(int num){
        this.num_attempt = num;
        writePref("num_attempt", num);
    }

    private void writePref(String key,String value){
        editor.putString(key, value);
        editor.commit();
    }

    private void writePref(String key,int value){
        editor.putInt(key, value);
        editor.commit();
    }

}
