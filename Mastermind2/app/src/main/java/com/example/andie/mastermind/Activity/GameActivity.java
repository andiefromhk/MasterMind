package com.example.andie.mastermind.Activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andie.mastermind.ActionBar.GameActionBar;
import com.example.andie.mastermind.Adapter.AttemptListAdapter;
import com.example.andie.mastermind.Controller.MMController;
import com.example.andie.mastermind.Fragment.ColorPickerFragment;
import com.example.andie.mastermind.Fragment.ColorPickerFragment_4Pins;
import com.example.andie.mastermind.Fragment.ColorPickerFragment_5Pins;
import com.example.andie.mastermind.Fragment.ColorPickerFragment_6Pins;
import com.example.andie.mastermind.Fragment.MMAnsDialogFragment;
import com.example.andie.mastermind.Fragment.MMAnsDialogHandler;
import com.example.andie.mastermind.Fragment.MMDialogFragment;
import com.example.andie.mastermind.Fragment.MMDialogHandler;
import com.example.andie.mastermind.Fragment.MMHandler;
import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.PinList;
import com.example.andie.mastermind.R;

import java.util.ArrayList;

public class GameActivity extends BaseActivity{

    ListView listView;
    ColorPickerFragment fragment=null;
    Button btn_guess, btn_play_pause;
    Handler handler;
    Runnable gTimeCounter;
    TextView timerTxt, attemptTxt;

    ArrayList<PinList> guessAttemptlist;
    ArrayList<Hint> hintAttemptlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_game);

        initGameInterface();
    }

    private void initGameInterface() {

        int gNumPin=mmc.currentSettingNumPin();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (fragment==null) {
            fragment = createColorPickerFragmentInstance(gNumPin);
            ft.add(R.id.guess_panel, fragment).commit();
        }

        GameActionBar actionBar = new GameActionBar(GameActivity.this, btn_handler_actionbar);

        listView = (ListView) findViewById(R.id.pAttemptDisplay);
        btn_guess = createBtn(btn_handler, R.id.btn_guess);
        btn_play_pause = createBtn(btn_handler, R.id.btn_play_pause);

        btn_guess.setEnabled(false);
        btn_play_pause.setText("Play");

        timerTxt = (TextView) findViewById(R.id.gTimer);
        attemptTxt =(TextView) findViewById(R.id.gAttemptRemain);
        attemptTxt.setText("");
        initTimerHandler();

        guessAttemptlist = new ArrayList<>();
        hintAttemptlist = new ArrayList<>();
        AttemptListAdapter pAdapter = new AttemptListAdapter(GameActivity.this);

        pAdapter.setGameData(gNumPin, guessAttemptlist, hintAttemptlist);
        listView.setAdapter(pAdapter);

        pAdapter.notifyDataSetChanged();
    }

    private void initTimerHandler() {
        handler= new Handler();

        gTimeCounter = new Runnable() {
            @Override
            public void run() {
                timerTxt.setText(mmc.timerTxtGenerator());
                mmc.increment_gTimeSpent(1000);
                handler.postDelayed(gTimeCounter, 1000);
            }
        };
    }

    private ColorPickerFragment createColorPickerFragmentInstance(int num) {
        ColorPickerFragment instance;
        switch(num){

            case 6:
                instance = new ColorPickerFragment_6Pins();
                break;
            case 5:
                instance = new ColorPickerFragment_5Pins();
                break;
            case 4:
            default:
                instance = new ColorPickerFragment_4Pins();
        }
        return instance;
    }

    @Override
    public void onResume(){
        super.onResume();

        if (mmc.gameRunningPaused()){
            resumeGame();
        }else initGameInterface();
    }

    @Override
    public void onPause(){
        super.onPause();
        pauseGame();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        endGame();
    }

    View.OnClickListener btn_handler = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_guess:
                    gGuessBtnCtrl();
                    break;
                case R.id.btn_play_pause:
                    gPlayPauseBtnCtrl();
                    break;
                default:
            }
        }
    };

    private void gGuessBtnCtrl() {
        if (mmc.gameRunningNotPaused()) {
            if (!mmc.completeGuessAttempt())
                Toast.makeText(getApplicationContext(), "The set of your guess is not complete!", Toast.LENGTH_SHORT).show();
            else{
                fragment.resetColorPickerBtn();
                mmc.increment_gAttempt();
                attemptTxt.setText("Remaining Attempt(s): " + mmc.remainingAttempt());

                if (mmc.checkIfBingo()){
                    showGameWinDialog();
                }else if (mmc.reachMaxAttempt()){
                    showGameLoseDialog();
                }else{ //game continues
                    hintAttemptlist.add(mmc.generateCurrentHint());
                    guessAttemptlist.add(mmc.currentGuess());
                    listView.post(new Runnable(){
                        public void run() {
                            listView.setSelection(listView.getCount() - 1);
                        }});
                    mmc.resetGuessAttempt();
                }
            }
        }
    }

    private void gPlayPauseBtnCtrl() {

        if (!mmc.gameRunning()){
            startGame();
        }
        else if (mmc.gameRunningNotPaused()){
            pauseGame();
            i = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(i);
        }
        else if (mmc.gameRunningPaused()){
            resumeGame();
        }
    }

    private void showGameWinDialog() {
        pauseGame();

        String timeSpent = "Time Spent: "+ mmc.timerTxtGenerator();
        String numAttempt = "Number of Attempt: "+ mmc.currentAttempt();
        showDialog("Bingo",
                timeSpent + "\n" + numAttempt +"\n" +
                        "Click OK to reset the game.", ansDialogHandler, true);
    }

    private void showGameLoseDialog() {
        pauseGame();
        showDialog("You Lose", "Click OK to reset the game.", ansDialogHandler, true);
    }

    private void startGame() {
        mmc.gameStart();
        fragment.colorPickerEnabled(true);
        btn_guess.setEnabled(true);
        btn_play_pause.setText("Pause");
        handler.post(gTimeCounter);
    }

    private void pauseGame() {
        mmc.gamePause();
        fragment.colorPickerEnabled(false);
        btn_guess.setEnabled(false);
        handler.removeCallbacks(gTimeCounter);

        if (mmc.gameRunning())
            btn_play_pause.setText("Resume");
    }

    private void resumeGame() {
        mmc.gameResume();
        fragment.colorPickerEnabled(true);
        btn_guess.setEnabled(true);
        btn_play_pause.setText("Pause");

        handler.post(gTimeCounter);
    }

    private void endGame() {
        mmc.gameEnd();
        fragment.colorPickerEnabled(false);
        btn_play_pause.setText("Play");
        timerTxt.setText("00:00");

        handler.removeCallbacks(gTimeCounter);
    }

    View.OnClickListener btn_handler_actionbar = new View.OnClickListener() {
        public void onClick(View v) {

            if (mmc==null)
                mmc=MMController.getInstance(getApplicationContext());

            switch(v.getId()) {

                case R.id.action_bar_home_game:
                    pauseGame();
                    showDialog("Are you sure you quit the game?", "Click OK to quit the game.", bkHomeHandler, false);
                    break;
                case R.id.action_bar_help_game:
                    if (mmc.gameRunningNotPaused())
                        mmc.gamePause();
                    i = new Intent(getApplicationContext(), HelpActivity.class);
                    startActivity(i);
                    break;
                case R.id.action_bar_giveup:
                    if (mmc.gameRunningNotPaused()) {
                        pauseGame();
                        showDialog("Are you sure you give up?", "Click OK to see the answer.", giveUpHandler, false);
                    }
                    break;

                default:
                    break;
            }
        }
    };

    private void back_to_Menu() {
        endGame();
        i = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(i);
    }

    private void showDialog(String title, String msg, MMHandler handler, boolean showAns){
        FragmentTransaction ft = getFragmentTransaction();

        DialogFragment dialogFragment;
        if (!showAns)
            dialogFragment = MMDialogFragment.getNewInstance(title, msg, (MMDialogHandler) handler);
        else
            dialogFragment = MMAnsDialogFragment.getNewInstance(mmc.currentAns(), title, msg, (MMAnsDialogHandler) handler);

        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");
    }

    @NonNull
    private FragmentTransaction getFragmentTransaction() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    MMAnsDialogHandler ansDialogHandler = new MMAnsDialogHandler() {
        @Override
        public void MMAnsClickOk() {
            endGame();
            initGameInterface();
        }
    };
    MMDialogHandler bkHomeHandler = new MMDialogHandler() {
        @Override
        public void MMDialogClickOk() {
            back_to_Menu();
        }

        @Override
        public void MMDialogClickCancel() {
            if (mmc.gameRunningPaused())
                resumeGame();
        }
    };

    MMDialogHandler giveUpHandler = new MMDialogHandler() {
        @Override
        public void MMDialogClickOk() {
            showDialog("You gave up", "Click OK to reset the game.", ansDialogHandler, true);
        }

        @Override
        public void MMDialogClickCancel() {
            if (mmc.gameRunningPaused())
                resumeGame();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mmc.gameRunningNotPaused())
                pauseGame();

            showDialog("Are you sure you quit the game?", "Click OK to quit the game.", bkHomeHandler, false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}