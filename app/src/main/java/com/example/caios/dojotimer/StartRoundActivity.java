package com.example.caios.dojotimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.Persistence.DatabaseManager;
import com.example.caios.dojotimer.Tatame.Adapter.HistoryRecycleViewAdapter;
import com.example.caios.dojotimer.Tatame.Interface.IClickListener;
import com.example.caios.dojotimer.Tatame.RoundFight;

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
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startround_main);
        DatabaseManager.init(this);
        history = new History(this);
//
//        edNamePLayerA = (EditText) findViewById(R.id.edNamePlayerA);
//        edNamePLayerB = (EditText) findViewById(R.id.edNamePlayerB);
//        txChronometer = (TextView) findViewById(R.id.txChronometer);
//        findViewById(R.id.llMinusTime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (timeRound > 0) {
//                    timeRound--;
//                    updateTimeValue(timeRound);
//                }
//            }
//        });
//
//        findViewById(R.id.llPlusTime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timeRound++;
//                updateTimeValue(timeRound);
//            }
//        });
//
//        findViewById(R.id.llNewRound).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startNewRound(edNamePLayerA.getText().toString(), edNamePLayerB.getText().toString());
//            }
//        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        HistoryRecycleViewAdapter adapter = new HistoryRecycleViewAdapter(history.getAllRouds(), new IClickListener() {
            @Override
            public void onClickItem(Round r) {
                Snackbar.make(recyclerView, "Tap on: " + r.getTimeRound(), Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
            Snackbar.make(recyclerView, "Adicione um nome ao Lutador B", Snackbar.LENGTH_SHORT).show();
        } else if (rightPlayer == null
                || rightPlayer.getName() == null
                || rightPlayer.getName().isEmpty()) {
            isValid = false;
            Snackbar.make(recyclerView, "Adicione um nome ao Lutador A", Snackbar.LENGTH_SHORT).show();
        } else if (timeRoundFormated == 0) {
            isValid = false;
            Snackbar.make(recyclerView, "Tempo de luta inválido", Snackbar.LENGTH_SHORT).show();
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
