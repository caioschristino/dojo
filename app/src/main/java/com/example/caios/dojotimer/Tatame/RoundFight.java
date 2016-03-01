package com.example.caios.dojotimer.Tatame;

import android.content.Context;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Tatame.Interface.IFinishTimer;
import com.example.caios.dojotimer.Tatame.Interface.IScoreboard;

import java.io.Serializable;

/**
 * Created by caios on 2/11/16.
 */
public class RoundFight implements IScoreboard, Serializable {
    private Player rightPlayer, leftPlayer;
    private long timeRound;
    private Stopwatch stopwatch;
    private IFinishTimer finishTimer;

    public RoundFight(Context context, long timeRound, Player leftPlayer, Player rightPlayer) {
        this.timeRound = timeRound;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.stopwatch = new Stopwatch(context, timeRound);
    }

    public void stopTime() {
        stopwatch.pause();
    }

    public void startTime() {
        stopwatch.create();
    }

    public long timeInStop() {
        return stopwatch.timePassed();
    }

    public long timeRound() {
        return timeRound;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    @Override
    public void addPoint(Player p) {
        if (p.equals(rightPlayer)) {
            rightPlayer.addPoint();
        } else {
            leftPlayer.addPoint();
        }
    }

    @Override
    public void addPenalty(Player p) {
        if (p.equals(rightPlayer)) {
            rightPlayer.addPenalty();
        } else {
            leftPlayer.addPenalty();
        }
    }

    @Override
    public void addAdvantage(Player p) {
        if (p.equals(rightPlayer)) {
            rightPlayer.addAdvantage();
        } else {
            leftPlayer.addAdvantage();
        }
    }

    @Override
    public void isFinishiTime() {
        stopwatch.onFinish(this);
    }

    public void addPainel(TextView textView) {
        stopwatch.addPainel(textView);
    }

    public boolean isStopped() {
        return stopwatch.isPaused();
    }

    public void addAction(IFinishTimer finishTimer) {
        this.finishTimer = finishTimer;
    }

    protected Object doAction(){
        return finishTimer.onFinish();
    }
}
