package com.example.caios.dojotimer.Persistence.DAO.Context;

import android.util.Log;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Persistence.DAO.ISimpleDAO;
import com.example.caios.dojotimer.Persistence.DatabaseManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by caios on 2/27/16.
 */
public class HistoryPlayer extends SimpleDAO {
    public HistoryPlayer() {
        super(Player.class);
    }

    @Override
    public List<? extends Player> getByQuery(String q) {
        try {
            final GenericRawResults<String[]> rawResults = getHelper().getPlayerDao().queryRaw(q);
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }
}