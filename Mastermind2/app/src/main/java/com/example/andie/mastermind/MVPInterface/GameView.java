package com.example.andie.mastermind.MVPInterface;

import android.app.Activity;

import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.PinColorList;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.MMHandler;

import java.util.ArrayList;

public interface GameView {
    void initGameUI(int numPin, ArrayList<PinColorList> guessList, ArrayList<Hint> hintList);
    void updateTimerTxt(String timeStr);
    void updateListViewSelection();
    void goToActivity(Class<? extends Activity> activity);
    void showGameWinDialog(String timeSpent, int attempt, PinColorList ans, MMHandler handler);
    void showGameLoseDialog(PinColorList ans, MMHandler handler);
    void showQuitGameDialog(MMHandler handler);
    void onIncompleteAttempt();
    void onCompleteAttempt(int remainingAttempt);
    void onStartGame();
    void onPauseGame();
    void onResumeGame();
    void onEndGame();
}
