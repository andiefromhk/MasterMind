package com.example.andie.mastermind.Model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public abstract class GameData extends Application {
    protected final Context context;
    protected final SharedPreferences.Editor editor;
    protected SharedPreferences prefs;

    protected GameData(Context context){
        this.context = context;
        prefs = this.context.getSharedPreferences(
                "mastermind", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    protected void writePref(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    protected void writePref(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }
}
