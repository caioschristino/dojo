package com.example.caios.dojotimer.Tatame;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caios.dojotimer.MainActivity;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.StartRoundActivity;
import com.example.caios.dojotimer.Tatame.Interface.IClickListener;
import com.example.caios.dojotimer.Tatame.Interface.IFinishTimer;

/**
 * Created by caios on 2/11/16.
 */
public class Stopwatch extends CountDownTimerDojo {
    private TextView txCurrentTime;
    private Context context;
    private History history;

    public Stopwatch(Context context, long millisOnTimer) {
        super(millisOnTimer, 1000, true);
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int seconds = (int) ((millisUntilFinished / 1000) % 60);
        int minutes = (int) ((millisUntilFinished / 1000) / 60);

        txCurrentTime.setText(String.format("%s:%s", minutes, seconds));
    }

    @Override
    public void onFinish(Object o) {
        txCurrentTime.setText("00:00");
        history = new History(context);

        RoundFight roundFight = (RoundFight) o;
        if (roundFight == null) {
            roundFight = history.getLastInsert();
            if (roundFight != null) {
                history.update(roundFight);
                Intent intent = new Intent(context, StartRoundActivity.class);
                context.startActivity(intent);
            }
        } else {
            roundFight.doAction();
        }
    }

    @Override
    public void addPainel(TextView textView) {
        this.txCurrentTime = textView;
    }
}
