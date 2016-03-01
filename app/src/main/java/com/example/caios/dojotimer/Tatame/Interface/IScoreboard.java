package com.example.caios.dojotimer.Tatame.Interface;

import com.example.caios.dojotimer.Models.Player;

/**
 * Created by caios on 2/14/16.
 */
public interface IScoreboard {
    void addPoint(Player p);

    void addPenalty(Player p);

    void addAdvantage(Player p);

    void isFinishiTime();
}