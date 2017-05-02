package com.example.andie.mastermind.Controller;

import android.content.Context;
import android.util.Log;

import com.example.andie.mastermind.Builder.HintBuilder;
import com.example.andie.mastermind.Builder.PinListBuilder;
import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.MMGameData;
import com.example.andie.mastermind.Model.MMSettingData;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.Model.PinList;

public class MMController{
    private final Context context;

    private MMSettingData settingData;
    private MMGameData gameData;
    private int num_pin, num_color;

    private PinList gGuess;
    private PinList gAns;

    private static MMController mmc;

    PinListBuilder gPinListBuilder;
    HintBuilder gHintBuilder;

    private MMController(Context context){
        this.context = context;

        this.settingData = MMSettingData.getInstance(context);
        this.gameData = MMGameData.getInstance();
        mmc = this;

        num_pin = settingData.get_num_pin();
        num_color = settingData.get_num_color();

        gPinListBuilder = new PinListBuilder(num_pin, num_color);
        gHintBuilder = new HintBuilder(num_color);
    }

    public static synchronized MMController getInstance(Context context){
        if (mmc == null) {
            mmc = new MMController(context);
        }
        return mmc;
    }

    public void gameStart(){

        this.settingData = MMSettingData.getInstance(context);
        this.gameData = MMGameData.getInstance();

        gameData.set_gRunning(true);

        num_pin = settingData.get_num_pin();
        num_color = settingData.get_num_color();

        gPinListBuilder = new PinListBuilder(num_pin, num_color);
        gHintBuilder =new HintBuilder(num_pin);

        gameData.set_gGuess(gPinListBuilder.resetPinList());
        gameData.set_gAns(gPinListBuilder.generateAnsList());

        gGuess = gameData.get_gGuess();
        gAns = gameData.get_gAns();
        Log.d(getClass().toString(),"Initial Guess : " + gGuess.toString());
        Log.d(getClass().toString(),"Ans : " + gAns.toString());

        gameData.set_gAttempt(0);
        gameData.set_gTimeSpent(0);
        gameData.set_gPause(false);
    }

    public void gameEnd(){

        gameData.set_gRunning(false);
        gameData.set_gGuess(null);
        gameData.set_gAns(null);
        gameData.set_gAttempt(0);
        gameData.set_gTimeSpent(0);
        gameData.set_gPause(false);

        gPinListBuilder = null;
        gHintBuilder = null;
    }

    public void gameResume(){
        gameData.set_gPause(false);
    }

    public void gamePause(){
        gameData.set_gPause(true);
    }

    public boolean gameRunningPaused(){
        return (gameData.get_gRunning() && gameData.get_gPause());
    }

    public boolean gameRunningNotPaused(){
        return (gameData.get_gRunning() && !gameData.get_gPause());
    }

    public void update_gGuess(int pos, PinColor pinColor){
        gGuess.setPin(pos, pinColor);
        gameData.set_gGuess(gGuess);
    }

    public void increment_gAttempt(){
        gameData.set_gAttempt(gameData.get_gAttempt()+1);
    }

    public void increment_gTimeSpent(long time){
        gameData.set_gTimeSpent(gameData.get_gTimeSpent()+ 1000);
    }

    public String timerTxtGenerator(){
        int seconds = (int) (gameData.get_gTimeSpent() / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String str_min=Integer.toString(minutes);
        String str_second=Integer.toString(seconds);

        if (minutes<1) str_min="00";
        if ((minutes>=1)&& (minutes<10))
            str_min="0"+str_min;
        if (seconds<10) str_second="0"+str_second;

        String str= str_min + ":" + str_second;

        return str;
    }

    public int remainingAttempt(){
        return settingData.get_num_attempt()- gameData.get_gAttempt();
    }

    public boolean completeGuessAttempt(){
        if (gGuess.isComplete())
            return true;
        else return false;
    }

    public void resetGuessAttempt(){
        gGuess = gPinListBuilder.resetPinList();
    }

    public boolean checkIfBingo(){
        if (gGuess.allColorMatches(gAns))
            return true;
        else return false;
    }

    public boolean reachMaxAttempt(){
        if (gameData.get_gAttempt() == settingData.get_num_attempt())
            return true;
        else return false;
    }
    public Hint generateCurrentHint(){
        return  gHintBuilder.generateHint(gGuess, gAns);
    }

    public PinList currentGuess(){
        return gameData.get_gGuess();
    }

    public PinList currentAns(){
        return gameData.get_gAns();
    }
    public int currentAttempt(){
        return gameData.get_gAttempt();
    }

    public boolean gameRunning(){
        return gameData.get_gRunning();
    }

    public String currentPlayerName(){
        return  settingData.getPlayerName();
    }

    public int currentSettingNumPin(){
        return settingData.get_num_pin();
    }

    public int currentSettingNumColor(){
        return settingData.get_num_color();
    }

    public  int currentSettingNumAttempt(){
        return settingData.get_num_attempt();
    }

    public void saveSettingData(String playerName, int pin, int color, int attempt){
        settingData.setPlayerName(playerName);
        settingData.set_num_pin(pin);
        settingData.set_num_color(color);
        settingData.set_num_attempt(attempt);
    }

}
