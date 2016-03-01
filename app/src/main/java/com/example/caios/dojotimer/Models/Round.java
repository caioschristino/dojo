package com.example.caios.dojotimer.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by caios on 2/20/16.
 */
@DatabaseTable
public class Round extends SimpleModel<Round> implements Serializable {
    public static final String ID = "ID";

    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;

    @DatabaseField(canBeNull = true)
    private long timeInStop;

    @DatabaseField(canBeNull = true)
    private long timeRound;

    @ForeignCollectionField
    private Collection<Player> playerCollection;

    public Round() {
    }

    public Round(long timeInStop, long timeRound) {
        this.timeInStop = timeInStop;
        this.timeRound = timeRound;
    }

    public long getTimeInStop() {
        return timeInStop;
    }

    public long getTimeRound() {
        return timeRound;
    }

    public Player getRightPlayer() {
        Player p = (Player) this.playerCollection.toArray()[0];
        return p;
    }

    public Player getLeftPlayer() {
        Player p = (Player) this.playerCollection.toArray()[1];
        return p;
    }

    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    @Override
    public Class<Round> getClassModel() {
        return super.getClassModel();
    }
}
