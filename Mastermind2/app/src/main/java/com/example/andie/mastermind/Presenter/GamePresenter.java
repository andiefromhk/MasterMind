package com.example.andie.mastermind.Presenter;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.example.andie.mastermind.Activity.HelpActivity;
import com.example.andie.mastermind.Activity.MenuActivity;
import com.example.andie.mastermind.MVPInterface.GameView;
import com.example.andie.mastermind.Manager.GameManager;
import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.PinColorList;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.MMHandler;

import java.util.ArrayList;

public class GamePresenter {
    GameView gameView;
    GameManager gameManager;
    Handler handler;
    Runnable gTimeCounter;
    ArrayList<PinColorList> guessList;
    ArrayList<Hint> hintList;
    boolean gameUI_init = false;

    private GamePresenter(){
        this.gameManager = GameManager.getInstance();
    }

    private static class InstanceHolder{
        private static final GamePresenter INSTANCE = new GamePresenter();
    }

    public static GamePresenter getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public void initGameActivity(GameView gView){
        gameView = gView;
        gameManager.init(((Activity) gView).getApplicationContext());

        guessList = new ArrayList<>();
        hintList = new ArrayList<>();

        initTimer();

        gameView.initGameUI(gameManager.getNumPin(), guessList, hintList);
        gameUI_init = true;

        if (gameManager.UINeedUpdate())
            gameManager.setUINeedUpdate(false);
    }

    private void startGame(){
        gameManager.initGameDataOnStart();
        gameView.onStartGame();
        resumeTimer();
    }

    private void pauseGame(){
        gameManager.setGameDataOnPause();
        gameView.onPauseGame();
        pauseTimer();
    }

    private void resumeGame(){
        gameManager.setGameDataOnResume();
        gameView.onResumeGame();
        resumeTimer();
    }

    private void endGame(){
        gameManager.setGameDataOnEnd();
        gameView.onEndGame();
        gameUI_init = false;
        endTimer();
    }

    private void initTimer(){
        handler= new Handler();

        gTimeCounter = new Runnable() {
            @Override
            public void run() {
                gameView.updateTimerTxt(gameManager.getTimeSpentStr());
                gameManager.incrementTimeSpent();
                handler.postDelayed(gTimeCounter, 1000);
            }
        };
    }

    private void pauseTimer(){
        if ((handler!= null) && (gTimeCounter != null))
            handler.removeCallbacks(gTimeCounter);
    }

    private void resumeTimer(){
        if (handler!= null)
            handler.post(gTimeCounter);
    }

    private void endTimer(){
        if ((handler!= null) && (gTimeCounter != null)){
            handler.removeCallbacks(gTimeCounter);
            handler = null;
        }
    }

    public void resumeGameActivity(GameView gView){
        if (gameManager.gameRunningPaused()){
            resumeGame();
        }else if (!gameManager.gameRunning() && (gameUI_init) && (gameManager.UINeedUpdate())){
            endGame();
            initGameActivity(gView);
        }
    }

    public void destroyGameActivity(){
        endGame();
    }

    public void guessBtnCtrl() {

        if (gameManager.gameRunningNotPaused()) {
            if (!gameManager.completeGuessAttempt()) {
                gameView.onIncompleteAttempt();
            }else{
                gameManager.incrementNumAttempt();
                gameView.onCompleteAttempt(gameManager.remainingAttempt());

                MMHandler handler = new MMHandler() {
                    @Override
                    public void clickOk() {
                        gameView.goToActivity(MenuActivity.class);
                    }
                    @Override
                    public void clickCancel() {
                    }
                };

                if (gameManager.checkIfBingo()){
                    pauseGame();
                    gameView.showGameWinDialog(gameManager.getTimeSpentStr(),
                                                gameManager.getNumAttempt(),
                                                gameManager.getGameAns(),
                                                handler
                                                );

                }else if (gameManager.reachMaxAttempt()){
                    pauseGame();
                    gameView.showGameLoseDialog(gameManager.getGameAns(), handler);
                }else{
                    hintList.add(gameManager.generateHint(gameManager.getCurrentGuess()));
                    guessList.add(gameManager.getCurrentGuess());

                    gameView.updateListViewSelection();
                    gameManager.resetGuessAttempt();
                }
            }
        }
    }

    public void playPauseBtnCtrl() {
        if (!gameManager.gameRunning()){
            startGame();
        }
        else if (gameManager.gameRunningNotPaused()){
            pauseGame();
            gameView.goToActivity(HelpActivity.class);
        }
        else if (gameManager.gameRunningPaused()){
            resumeGame();
        }
    }

    public void actionHomeCtrl(){
        if (!gameManager.gameRunning()){
            gameView.goToActivity(MenuActivity.class);
        }
        else{
            if (gameManager.gameRunningNotPaused())
                pauseGame();
            showQuitGameDialog();
        }
    }

    private void showQuitGameDialog(){
        MMHandler handler = new MMHandler() {
            @Override
            public void clickOk() {
                endGame();
                gameView.goToActivity(MenuActivity.class);
            }
            @Override
            public void clickCancel() {
                resumeGame();
            }
        };
        gameView.showQuitGameDialog(handler);
    }


    public void actionHelpCtrl(){
        if (gameManager.gameRunningNotPaused()){
            pauseGame();
        }
        gameView.goToActivity(HelpActivity.class);
    }

    public void keyBackCtrl(){
        actionHomeCtrl();
    }

}
