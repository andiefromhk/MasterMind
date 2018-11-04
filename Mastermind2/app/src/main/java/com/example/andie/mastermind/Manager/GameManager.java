package com.example.andie.mastermind.Manager;

import android.content.Context;
import android.util.Log;

import com.example.andie.mastermind.Model.GameStatusData;
import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.HintSymbol;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.Model.PinColorList;
import com.example.andie.mastermind.Model.SettingData;
import com.example.andie.mastermind.Utils.MMUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    private SettingData settingData;
    private GameStatusData gameStatusData;
    private int numPin;
    private int numColor;
    private PinColorList currentGuess;
    private PinColorList gameAns;
    private boolean UINeedUpdate = false;

    private GameManager(){}

    private static class InstanceHolder{
        private static final GameManager INSTANCE = new GameManager();
    }

    public static GameManager getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public void init(Context context){

        if (InstanceHolder.INSTANCE != null) {
            this.settingData = SettingData.getInstance(context);
            this.gameStatusData = GameStatusData.getInstance(context);

            this.numPin = settingData.getNumPin();
            this.numColor = settingData.getNumColor();

            Log.d(getClass().toString(), "gsData initGameDataOnResume b4 set gRunning gPause");

            gameStatusData.set_gRunning(false);
            gameStatusData.set_gPause(false);
            gameStatusData.setNumAttempt(0);
            gameStatusData.setTimeSpent(0);

        }
    }

    public int getNumPin(){
       return this.numPin;
    }
    public int getNumColor(){
        return this.numColor;
    }

    public void initGameDataOnStart(){

        currentGuess = getEmptyPinArr(numPin);
        gameAns = generateAns();

        gameStatusData.set_gRunning(true);
        gameStatusData.set_gPause(false);
        gameStatusData.setNumAttempt(0);
        gameStatusData.setTimeSpent(0);
    }

    public void setGameDataOnEnd(){

        currentGuess = null;
        gameAns = null;

        gameStatusData.set_gRunning(false);
        gameStatusData.set_gPause(false);
        gameStatusData.setNumAttempt(0);
        gameStatusData.setTimeSpent(0);

    }

    public void setGameDataOnResume(){
        gameStatusData.set_gPause(false);
    }

    public void setGameDataOnPause(){
        gameStatusData.set_gPause(true);
    }

    public boolean gameRunning(){
        return gameStatusData.get_gRunning();
    }

    public boolean gameRunningPaused(){
        return (gameStatusData.get_gRunning() && gameStatusData.get_gPause());
    }

    public boolean gameRunningNotPaused(){
        return (gameStatusData.get_gRunning() && !gameStatusData.get_gPause());
    }

    public int remainingAttempt(){
        return settingData.getNumAttempt() - gameStatusData.getNumAttempt();
    }

    public int getNumAttempt(){
        return gameStatusData.getNumAttempt();
    }

    public void incrementNumAttempt(){
        gameStatusData.incrementNumAttempt();
    }

    public void incrementTimeSpent(){
        gameStatusData.incrementTimeSpent();
    }

    public String getTimeSpentStr(){
        return MMUtils.getTimerTxt(gameStatusData.getTimeSpent());
    }

    public boolean completeGuessAttempt(){

        return currentGuess.isComplete();
    }

    public void updateCurrentGuess(int pos, PinColor pinColor){
        currentGuess.setPin(pos, pinColor);
    }

    public PinColorList getCurrentGuess(){
        return currentGuess;
    }
    public boolean checkIfBingo(){
        return currentGuess.allColorMatches(gameAns);
    }


    public boolean reachMaxAttempt(){
        return (gameStatusData.getNumAttempt() == settingData.getNumAttempt());
    }

    public PinColorList getEmptyPinArr(int numPin){
        return new PinColorList(numPin);
    }

    public void resetGuessAttempt(){
        currentGuess = getEmptyPinArr(settingData.getNumPin());
    }

    public PinColorList getGameAns(){
        return gameAns;
    }

    public PinColorList generateAns() {
        int min = 1;
        int max = numColor + 1;
        PinColorList ans = new PinColorList(numPin);

        for(int i=0; i< numPin; i++){
            int rNum = ThreadLocalRandom.current().nextInt(min, max);
            PinColor pinColor = PinColor.getPinColorByOrdinal(rNum);
            ans.setPin(i, pinColor);
        }
        return ans;
    }

    public Hint generateHint(PinColorList guess){
        Hint hint = new Hint();
        int num_match = 0;
        int num_colorOnly = 0;
        int num_notMatch = 0;

        List<PinColor> guessCopy = guess.getCopyList();
        List<PinColor> ansCopy = gameAns.getCopyList();

        Log.d(getClass().toString(), "genHint CurrentGuess = "+ guess.toString());
        Log.d(getClass().toString(), "genHint GameAns = "+ gameAns.toString());

        List<Integer> posMatch = new ArrayList<>();

        for(int i=0; i < numPin; i++){
            if (guessCopy.get(i).equals(ansCopy.get(i))){
                posMatch.add(i);
                num_match++;
            }
        }

        Log.d(getClass().toString(), "genHint posMatch = "+ posMatch.toString());

        if (!posMatch.isEmpty()){
            int r = 0;
            for(int entry : posMatch){
                guessCopy.remove(entry -r);
                ansCopy.remove(entry - r);
                r++;
            }
        }

        Log.d(getClass().toString(), "genHint GuessCopy = "+ guessCopy.toString());
        Log.d(getClass().toString(), "genHint AnsCopy = "+ ansCopy.toString());


        Map<PinColor, Integer> ansCnts = getCntsForHints(ansCopy);
        Map<PinColor, Integer> guessCnts = getCntsForHints(guessCopy);

        Log.d(getClass().toString(), "genHint ansCnts = "+ ansCnts.toString());
        Log.d(getClass().toString(), "genHint guessCnts = "+ guessCnts.toString());

        for (Map.Entry<PinColor, Integer> entry : ansCnts.entrySet()) {
            if (guessCnts.containsKey(entry.getKey())){
                if (guessCnts.get(entry.getKey()) >= entry.getValue()){
                    num_colorOnly += entry.getValue();
                }else if (guessCnts.get(entry.getKey()) < entry.getValue()){
                    num_colorOnly += guessCnts.get(entry.getKey());
                }
            }
        }

        num_notMatch = numPin - num_match - num_colorOnly;

        for (int i=0; i<num_match; i++)
            hint.addHintSymbol(HintSymbol.Match);
        for (int i=0; i<num_colorOnly; i++)
            hint.addHintSymbol(HintSymbol.ColorOnly);
        for (int i=0; i<num_notMatch; i++)
            hint.addHintSymbol(HintSymbol.NotMatch);

        return hint;
    }

    private Map<PinColor, Integer> getCntsForHints(List<PinColor> list){
        Map<PinColor, Integer> cnts = new HashMap<>();

        for (PinColor item: list) {
            if (cnts.containsKey(item))
                cnts.put(item, cnts.get(item) + 1);
            else
                cnts.put(item, 1);
        }
        return cnts;
    }

    public String getPlayerGame(){
        return settingData.getPlayerName();
    }

    public int getNumAttemptSetting(){
        return settingData.getNumAttempt();
    }

    public void saveSettingData(String playerName, int pin, int color, int attempt){
        settingData.setPlayerName(playerName);
        settingData.set_num_pin(pin);
        settingData.set_num_color(color);
        settingData.set_num_attempt(attempt);
        this.UINeedUpdate = true;
    }

    public boolean UINeedUpdate(){
        return this.UINeedUpdate;
    }

    public void setUINeedUpdate(boolean needUpdate) {
        this.UINeedUpdate = needUpdate;
    }
}
