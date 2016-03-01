package com.example.caios.dojotimer.Persistence;

/**
 * Created by caios on 2/20/16.
 */
import android.content.Context;

public class DatabaseManager {
    static private DatabaseManager instance;
    static protected String DB_LOG = "DbLog";
    static protected Context _ctx;

    static public void init(Context ctx) {
        if (null==instance) {
            _ctx = ctx;
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private static DatabaseHelper helper;
    public DatabaseManager() {
    }
    public DatabaseManager(Context ctx) {
        _ctx = ctx;
        helper = new DatabaseHelper(ctx);
        helper.getWritableDatabase();
    }

    protected static DatabaseHelper getHelper() {
        return helper;
    }
}