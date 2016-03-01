package com.example.caios.dojotimer.Persistence.DAO.Context;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Models.SimpleModel;
import com.example.caios.dojotimer.Persistence.DatabaseManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by caios on 2/29/16.
 */
public abstract class SimpleDAO<T> extends DatabaseManager {
    Dao<T, Integer> dao;


    public SimpleDAO(Class<T> clazz) {
        super();
        DatabaseManager.getInstance();
        try {
            dao = getHelper().getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("==>", e.getMessage());
        }
    }

    public T getLastInsert() {
        QueryBuilder<T, Integer> query = null;
        try {
            query = dao.queryBuilder();
            query.orderBy(Round.ID, false);
            return query.queryForFirst();
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }

    public T get(int id) {
        QueryBuilder<T, Integer> query = null;
        try {
            query = dao.queryBuilder();
            query.where().eq(Round.ID, id);
            return query.queryForFirst();
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }

    public List<T> getAll() {
        final QueryBuilder<T, Integer> query;
        try {
            query = dao.queryBuilder();
            return query.query();
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }

    public List<T> getAll(List<Integer> ids) {
        final QueryBuilder<T, Integer> query;
        try {
            query = dao.queryBuilder();
            return query.query();
        } catch (SQLException e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }

    public T createOrUpdate(T t) {
        try {
            dao.createOrUpdate(t);
            return dao.queryForSameId(t);
        } catch (final Exception e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }

    public T update(T t) {
        try {
            dao.update(t);
            return dao.queryForSameId(t);
        } catch (final Exception e) {
            Log.e("==>", e.getMessage());
        }
        return null;
    }


    public void createOrUpdate(List<T> o) {
        for (T obj : o) {
            try {
                dao.createOrUpdate(obj);
            } catch (final Exception e) {
                Log.e("==>", e.getMessage());
            }
        }
    }

    public void delete(T t) {
        try {
            dao.delete(t);
        } catch (final Exception e) {
            Log.e("==>", e.getMessage());
        }
    }

    public void delete(List<T> o) {
        for (T obj : o) {
            try {
                dao.delete(obj);
            } catch (final Exception e) {
                Log.e("==>", e.getMessage());
            }
        }
    }

    public abstract List<T> getByQuery(String q);
}
