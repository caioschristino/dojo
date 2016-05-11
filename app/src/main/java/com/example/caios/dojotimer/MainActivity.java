package com.example.caios.dojotimer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.Tatame.BlowType;
import com.example.caios.dojotimer.Tatame.Interface.IFinishTimer;
import com.example.caios.dojotimer.Tatame.RoundFight;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RoundFight roundFight;
    private History history;
    private TextView textPoints, textAdvantagePlayerA, textPenaltyPlayerA,
            textAdvantagePlayerB, textPenaltyPlayerB, txChronometer, txNamePlayerB, txNamePlayerA;


    private LinearLayout llPausePlay, llReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPoints = (TextView) findViewById(R.id.textViewPoints);
        textAdvantagePlayerA = (TextView) findViewById(R.id.textAdvantagePlayerA);
        textPenaltyPlayerA = (TextView) findViewById(R.id.textPenaltyPlayerA);
        textAdvantagePlayerB = (TextView) findViewById(R.id.textAdvantagePlayerB);
        textPenaltyPlayerB = (TextView) findViewById(R.id.textPenaltyPlayerB);
        txChronometer = (TextView) findViewById(R.id.txChronometer);
        txNamePlayerB = (TextView) findViewById(R.id.txNamePlayerB);
        txNamePlayerA = (TextView) findViewById(R.id.txNamePlayerA);

        history = new History(this);
        setCurrentRound();
        startChronometer();

        llPausePlay = (LinearLayout) findViewById(R.id.llPausePlay);
        llPausePlay.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                ImageView pauseplay = (ImageView) findViewById(R.id.ivPausePlay);
                if (roundFight.isStopped()) {
                    pauseplay.setImageResource(R.drawable.ic_play);
                    resetScoreboard(roundFight);
                    enableButtonsToIncrementScore(true);
                    roundFight.startTime();
                } else {
                    pauseplay.setImageResource(R.drawable.ic_pause);
                    enableButtonsToIncrementScore(false);
                    roundFight.stopTime();
                }
            }
        });

        llReset = (LinearLayout) findViewById(R.id.llReset);
        llReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRound();
            }
        });
    }

    private void resetRound() {
        roundFight.addAction(new IFinishTimer() {
            @Override
            public Object onFinish() {
                saveRoundFight(roundFight);
                Intent intent = new Intent(MainActivity.this, StartRoundActivity.class);
                startActivity(intent);
                return null;
            }
        });

        roundFight.isFinishiTime();
    }

    private void resetScoreboard(RoundFight roundFight) {
        if (!roundFight.isStopped()) {
            updateScore();
        }
    }

    public void onClickPlayerB(View v) {
        final int id = v.getId();
        boolean isAPoint = validAttack(id, roundFight.getLeftPlayer());
        if (isAPoint) {
            roundFight.addPoint(roundFight.getLeftPlayer());
        }
        updateScore();
    }

    public void onClickPlayerA(View v) {
        final int id = v.getId();
        boolean isAPoint = validAttack(id, roundFight.getRightPlayer());

        if (isAPoint) {
            roundFight.addPoint(roundFight.getRightPlayer());
        }
        updateScore();
    }

    private boolean validAttack(int id, Player p) {
        boolean isAPoint = true;
        try {
            switch (id) {
                case R.id.fabFourpointsPlayerA:
                    p.addAttack(BlowType.addFourPoints());
                case R.id.fabThreepointsPlayerA:
                    p.addAttack(BlowType.addThreePoints());
                case R.id.fabTwopointsPlayerA:
                    p.addAttack(BlowType.addTwoPoints());
                    break;
                case R.id.fabPunishmentsPlayerA:
                    roundFight.addPenalty(p);
                    isAPoint = false;
                    break;
                case R.id.fabAdvantagesPlayerA:
                    roundFight.addAdvantage(p);
                    isAPoint = false;
                    break;
            }
        } catch (final Exception e) {

        }
        return isAPoint;
    }

    private void saveRoundFight(RoundFight roundFight) {
        enableButtonsToIncrementScore(false);
        history.update(roundFight);
    }

    private void updateScore() {
        textPenaltyPlayerA.setText(String.valueOf(roundFight.getRightPlayer().getPenalty()));
        textAdvantagePlayerA.setText(String.valueOf(roundFight.getRightPlayer().getAdvantage()));
        textPoints.setText(String.format("%s x %s", roundFight.getLeftPlayer().getScore(), roundFight.getRightPlayer().getScore()));
        textPenaltyPlayerB.setText(String.valueOf(roundFight.getLeftPlayer().getPenalty()));
        textAdvantagePlayerB.setText(String.valueOf(roundFight.getLeftPlayer().getAdvantage()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enableButtonsToIncrementScore(boolean b) {
        if (b) {
            enableElemets(R.id.llButtonsPlayerA);
            enableElemets(R.id.llButtonsPlayerB);
        } else {
            disableElemets(R.id.llButtonsPlayerA);
            disableElemets(R.id.llButtonsPlayerB);
        }
    }


    private void enableElemets(int element) {
        LinearLayout myLayout = (LinearLayout) findViewById(element);
        for (int i = 0; i < myLayout.getChildCount(); i++) {
            View view = myLayout.getChildAt(i);
            view.setEnabled(true);
        }
    }

    private void disableElemets(int element) {
        LinearLayout myLayout = (LinearLayout) findViewById(element);
        for (int i = 0; i < myLayout.getChildCount(); i++) {
            View view = myLayout.getChildAt(i);
            view.setEnabled(false);
        }
    }

    private void setCurrentRound() {
        roundFight = (RoundFight) history.getLastInsert();
        roundFight.addPainel(txChronometer);
        txNamePlayerA.setText(String.format("Lutador: %s", roundFight.getRightPlayer().getName()));
        txNamePlayerB.setText(String.format("Lutador: %s", roundFight.getLeftPlayer().getName()));
    }

    private void startChronometer() {
        roundFight.startTime();
    }
}