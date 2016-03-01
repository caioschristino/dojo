package com.example.caios.dojotimer.Persistence.Controller;

import android.content.Context;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.DAO.Context.HistoryFight;
import com.example.caios.dojotimer.Persistence.DAO.Context.HistoryPlayer;
import com.example.caios.dojotimer.Tatame.RoundFight;

import java.util.List;

/**
 * Created by caios on 2/20/16.
 */
public class History {
    private HistoryFight historyFight;
    private HistoryPlayer historyPlayer;
    private Context context;

    public History(Context context) {
        this.context = context;
        historyFight = new HistoryFight();
        historyPlayer = new HistoryPlayer();
    }

    public void addRound(RoundFight roundFight) {
        Round r = new Round(roundFight.timeInStop(),
                roundFight.timeRound());
        historyFight.createOrUpdate(r);

        roundFight.getLeftPlayer().addRound(r);
        historyPlayer.createOrUpdate(roundFight.getLeftPlayer());
        roundFight.getRightPlayer().addRound(r);
        historyPlayer.createOrUpdate(roundFight.getRightPlayer());
    }

    public void update(RoundFight roundFight) {
        Round r = new Round(roundFight.timeInStop(),
                roundFight.timeRound());

        Player leftPlayer = roundFight.getLeftPlayer();
        historyPlayer.update(leftPlayer);

        Player rightPlayer = roundFight.getRightPlayer();
        historyPlayer.update(rightPlayer);

        historyFight.update(r);
    }

    public List<Round> getAllRouds() {
        return (List<Round>) historyFight.getAll();
    }

    public List<Player> getAllPlayersInRounds() {
        return (List<Player>) historyPlayer.getAll();
    }

    public RoundFight getLastInsert() {
        Round round = (Round) historyFight.getLastInsert();
        return new RoundFight(context,
                round.getTimeRound(),
                round.getLeftPlayer(),
                round.getRightPlayer());
    }
}
