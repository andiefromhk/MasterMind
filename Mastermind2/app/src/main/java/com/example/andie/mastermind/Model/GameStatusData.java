package com.example.andie.mastermind.Model;

import android.content.Context;

public class GameStatusData extends GameData{
    private static GameStatusData gsData;
    private static boolean gRunning = false;
    private static boolean gPause = false;
    private static int numAttempt = 0;
    private static long timeSpent = 0;

    private GameStatusData(Context context){
        super(context);
        gsData = this;
    }

    public static GameStatusData getInstance(Context context){
        if (gsData == null) {
            gsData = new GameStatusData(context);
        }
        return gsData;
    }
    public boolean get_gRunning(){
        return gRunning;
    }

    public void set_gRunning(boolean running){
        gRunning = running;
    }

    public boolean get_gPause(){
        return gPause;
    }

    public void set_gPause(boolean pause){
        gPause = pause;
    }

    public int getNumAttempt(){
        return numAttempt;
    }

    public void setNumAttempt(int attempt){
        numAttempt = attempt;
    }

    public void incrementNumAttempt(){
        numAttempt++;
    }

    public long getTimeSpent(){
        return timeSpent;
    }

    public void setTimeSpent(long time){
        timeSpent = time;
    }

    public void incrementTimeSpent(){
        timeSpent += 1000;
    }
}
