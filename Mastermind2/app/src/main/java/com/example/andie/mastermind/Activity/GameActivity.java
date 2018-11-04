package com.example.andie.mastermind.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andie.mastermind.MVPInterface.GameView;
import com.example.andie.mastermind.Presenter.GamePresenter;
import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.PinColorList;
import com.example.andie.mastermind.R;
import com.example.andie.mastermind.UIComponents.UIComponents.ActionBar.GameActionBar;
import com.example.andie.mastermind.UIComponents.UIComponents.Adapter.AttemptListAdapter;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment_4Pins;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment_5Pins;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.ColorPickerFragment_6Pins;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.MMAnsDialogFragment;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.MMDialogFragment;
import com.example.andie.mastermind.UIComponents.UIComponents.Fragments.MMHandler;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends BaseActivity implements GameView {

    ListView listView;
    ColorPickerFragment colorPickerFragment = null;
    Button btn_guess, btn_play_pause;
    TextView timerTxt, attemptTxt;
    GameActionBar actionBar;
    GamePresenter gamePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_game);

        gamePresenter = GamePresenter.getInstance();
        gamePresenter.initGameActivity(this);
    }

    public void initGameUI(int gameNumPin, ArrayList<PinColorList> guessList, ArrayList<Hint> hintList) {
        listView = (ListView) findViewById(R.id.pAttemptDisplay);

        btn_guess = createBtn(new GameButtonClick(), R.id.btn_guess);
        btn_play_pause = createBtn(new GameButtonClick(), R.id.btn_play_pause);
        btn_guess.setEnabled(false);
        btn_play_pause.setText("Play");

        timerTxt = (TextView) findViewById(R.id.gTimer);
        attemptTxt =(TextView) findViewById(R.id.gAttemptRemain);
        attemptTxt.setText("");

        actionBar = new GameActionBar(this, new GameActionBarButtonClick());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (colorPickerFragment == null) {
            colorPickerFragment = createColorPickerFragmentInstance(gameNumPin);
            ft.add(R.id.guess_panel, colorPickerFragment).commit();
        }

        AttemptListAdapter gAdapter = new AttemptListAdapter(GameActivity.this);

        gAdapter.setGameData(gameNumPin, guessList, hintList);
        listView.setAdapter(gAdapter);

        gAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        gamePresenter.resumeGameActivity(this);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        gamePresenter.destroyGameActivity();
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

    public void updateTimerTxt(String timeStr){
        timerTxt.setText(timeStr);
    }

    public void updateListViewSelection(){
        listView.post(new Runnable(){
            public void run() {
                listView.setSelection(listView.getCount() - 1);
            }});
    }

    public void onIncompleteAttempt(){
        Toast.makeText(getApplicationContext(), "Your guess is not complete!", Toast.LENGTH_SHORT).show();
    }

    public void onCompleteAttempt(int remainingAttempt){
        colorPickerFragment.resetColorPickerBtn();
        attemptTxt.setText("Remaining Attempt(s): " + remainingAttempt);
    }

    public void onStartGame() {
        colorPickerFragment.colorPickerEnabled(true);
        btn_guess.setEnabled(true);
        btn_play_pause.setText("Pause");
    }

    public void onPauseGame() {
        colorPickerFragment.colorPickerEnabled(false);
        btn_guess.setEnabled(false);
        btn_play_pause.setText("Resume");
    }

    public void onResumeGame() {
        colorPickerFragment.colorPickerEnabled(true);
        btn_guess.setEnabled(true);
        btn_play_pause.setText("Pause");
    }

    public void onEndGame() {
        colorPickerFragment.colorPickerEnabled(false);
        btn_play_pause.setText("Play");
        timerTxt.setText("00:00");
    }

    public void goToActivity(Class<? extends Activity> activity){
        startActivity(new Intent(getApplicationContext(), activity));
    }
    public void showGameWinDialog(String timeSpent, int numAttempt, PinColorList ans, MMHandler handler) {

        String title = getString(R.string.bingo);
        String msgTimeSpent = getString((R.string.time_spent)) + timeSpent;
        String msgNumAttempt = getString(R.string.num_attempt) + numAttempt;
        String msg = String.format("%s \n %s", msgTimeSpent, msgNumAttempt);

        showDialogWithAns(ans, title, msg, handler, "gameWinDialog");
    }

    public void showGameLoseDialog(PinColorList ans, MMHandler handler) {

        String title = getString(R.string.lose);
        String msg = getString(R.string.ok_to_reset);

        showDialogWithAns(ans, title, msg, handler, "gameLoseDialog");
    }

    private void showDialogWithAns(PinColorList ans, String title, String msg, MMHandler handler, String tag){
        MMAnsDialogFragment dialog = MMAnsDialogFragment.getNewInstance(ans, title, msg, handler);
        FragmentTransaction ft = getFragmentTransaction();
        dialog.setCancelable(false);
        dialog.show(ft, tag);
    }

    public void showQuitGameDialog(MMHandler handler){
        MMDialogFragment dialog = MMDialogFragment.getNewInstance("Are you sure to quit the game?", "Click OK to quit.", handler);
        dialog.setCancelable(false);
        dialog.show(getFragmentTransaction(), "quitGameDialog");
    }

    @NonNull
    private FragmentTransaction getFragmentTransaction(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<Fragment> prevFragments = fm.getFragments();
        if ((prevFragments != null) && (!prevFragments.isEmpty())){
            for (Fragment fragment : prevFragments) {
                ft.remove(fragment);
            }
        }
        ft.addToBackStack(null);
        return ft;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            gamePresenter.keyBackCtrl();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class GameActionBarButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.action_bar_home_game:
                    gamePresenter.actionHomeCtrl();
                    break;
                case R.id.action_bar_help_game:
                    gamePresenter.actionHelpCtrl();
                    break;
                default:
                    break;
            }
        }
    }

    class GameButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_guess:
                    gamePresenter.guessBtnCtrl();
                    break;
                case R.id.btn_play_pause:
                    gamePresenter.playPauseBtnCtrl();
                    break;
                default:
            }
        }
    }

}