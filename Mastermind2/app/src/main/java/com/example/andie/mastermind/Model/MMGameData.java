package com.example.andie.mastermind.Model;

public class MMGameData {
    private static MMGameData mmGame;
    private boolean gRunning;
    private PinList gGuess;  //current guess
    private PinList gAns;    //current answer
    private int gAttempt;
    private long gTimeSpent;
    private boolean gPause;

    private MMGameData(){
        this.mmGame = this;
    }
    public static synchronized MMGameData getInstance(){
        if (mmGame == null) {
            mmGame = new MMGameData();
        }
        return mmGame;
    }
    public boolean get_gRunning(){
        return gRunning;
    }

    public void set_gRunning(boolean running){
        this.gRunning = running;
    }

    public PinList get_gGuess(){
        return gGuess;
    }

    public void set_gGuess(PinList list){
        this.gGuess = list;
    }

    public PinList get_gAns(){
        return gAns;
    }

    public void set_gAns(PinList list){
        this.gAns = list;
    }

    public int get_gAttempt(){
        return gAttempt;
    }

    public void set_gAttempt(int attempt){
        this.gAttempt = attempt;
    }

    public long get_gTimeSpent(){
        return gTimeSpent;
    }

    public void set_gTimeSpent(long timeSpent){
        this.gTimeSpent = timeSpent;
    }

    public boolean get_gPause(){
        return gPause;
    }

    public void set_gPause(boolean pause){
        this.gPause=pause;
    }
}
