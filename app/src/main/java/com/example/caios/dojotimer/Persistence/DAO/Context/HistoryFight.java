package com.example.caios.dojotimer.Persistence.DAO.Context;

import android.os.AsyncTask;
import android.util.Log;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Models.SimpleModel;
import com.example.caios.dojotimer.Persistence.DAO.ISimpleDAO;
import com.example.caios.dojotimer.Persistence.DatabaseManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by caios on 2/20/16.
 */
public class HistoryFight  extends SimpleDAO {
    public HistoryFight() {
        super(Round.class);
    }

    @Override
    public List<? extends Round> getByQuery(String q) {
        try {
            final GenericRawResults<String[]> rawResults = getHelper().getPlayerDao().queryRaw(q);
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }
}
