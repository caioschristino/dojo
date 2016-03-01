package com.example.caios.dojotimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.Persistence.DatabaseManager;
import com.example.caios.dojotimer.Tatame.RoundFight;
import com.example.caios.dojotimer.Tatame.Stopwatch;
import com.j256.ormlite.dao.CloseableIterator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by caios on 2/27/16.
 */
public class StartRoundActivity extends AppCompatActivity {
    private RoundFight roundFight;
    private Player rightPlayer, leftPlayer;
    private EditText edNamePLayerA, edNamePLayerB;
    private TextView txChronometer;
    private long timeRound;
    private History history;
    private Button btConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_round);
        DatabaseManager.init(this);

        edNamePLayerA = (EditText) findViewById(R.id.edNamePlayerA);
        edNamePLayerB = (EditText) findViewById(R.id.edNamePlayerB);
        txChronometer = (TextView) findViewById(R.id.txChronometer);

        history = new History(this);

        findViewById(R.id.btRemoveTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeRound > 0) {
                    timeRound--;
                    updateTimeValue(timeRound);
                }
            }
        });

        findViewById(R.id.btAddTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRound++;
                updateTimeValue(timeRound);
            }
        });

        findViewById(R.id.btNewRound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewRound(edNamePLayerA.getText().toString(), edNamePLayerB.getText().toString());
            }
        });

        btConsulta = (Button) findViewById(R.id.btConsulta);
        btConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartRoundActivity.this, HistoryRoundActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateTimeValue(long time) {
        txChronometer.setText(String.format("%s:00", time));
    }


    public void startNewRound(String namePlayerA, String namePlayerB) {
        leftPlayer = new Player(namePlayerB);
        rightPlayer = new Player(namePlayerA);
        long timeRoundFormated = getTimeFormated();

        if (isValidRound(leftPlayer, rightPlayer, timeRoundFormated)) {
            roundFight = new RoundFight(this, timeRoundFormated, leftPlayer, rightPlayer);

            saveRoundFight(roundFight);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean isValidRound(Player leftPlayer, Player rightPlayer, long timeRoundFormated) {
        boolean isValid = true;
        if (leftPlayer == null
                || leftPlayer.getName() == null
                || leftPlayer.getName().isEmpty()) {
            isValid = false;
            Snackbar.make(btConsulta, "Adicione um nome ao Lutador B", Snackbar.LENGTH_SHORT).show();
        } else if (rightPlayer == null
                || rightPlayer.getName() == null
                || rightPlayer.getName().isEmpty()) {
            isValid = false;
            Snackbar.make(btConsulta, "Adicione um nome ao Lutador A", Snackbar.LENGTH_SHORT).show();
        } else if (timeRoundFormated == 0) {
            isValid = false;
            Snackbar.make(btConsulta, "Tempo de luta inválido", Snackbar.LENGTH_SHORT).show();
        }
        return isValid;
    }

    private void saveRoundFight(RoundFight roundFight) {
        history.addRound(roundFight);
    }

    private long getTimeFormated() {
        return TimeUnit.MINUTES.toMillis(timeRound);
    }
}
