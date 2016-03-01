package com.example.caios.dojotimer.Models;

import com.example.caios.dojotimer.Tatame.Interface.IBlow;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by caios on 2/11/16.
 */
@DatabaseTable
public class Player extends SimpleModel<Player> implements Serializable {
    public static final String ID = "ID";
    public static final String ACCOUNT_ID_FIELD_NAME = "round_id";

    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;

    @DatabaseField(canBeNull = true)
    private int score;

    @DatabaseField(canBeNull = true)
    private String name;

    @DatabaseField(canBeNull = true)
    private int penalty;

    @DatabaseField(canBeNull = true)
    private int advantage;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ACCOUNT_ID_FIELD_NAME)
    private Round round;

    private IBlow IBlow;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public void addRound(Round r) {
        this.round = r;
    }

    public int getAdvantage() {
        return advantage;
    }

    public int getPenalty() {
        return penalty;
    }

    public void addPoint() {
        this.score += IBlow.attack();
    }

    public void addPenalty() {
        penalty++;
    }

    public void addAdvantage() {
        advantage++;
    }

    public String getName() {
        return name;
    }

    public void resetScore() {
        score = 0;
    }

    public void addAttack(IBlow b) {
        this.IBlow = b;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    @Override
    public Class<Player> getClassModel() {
        return super.getClassModel();
    }
}
