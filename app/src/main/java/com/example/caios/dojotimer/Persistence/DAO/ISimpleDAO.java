package com.example.caios.dojotimer.Persistence.DAO;

import com.example.caios.dojotimer.Tatame.RoundFight;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by caios on 2/20/16.
 */
public interface ISimpleDAO<T> {
    T getLastInsert();

    T get(int id);

    List<T> getAll();

    List<T> getAll(List<Integer> ids);

    T createOrUpdate(T t);

    T update(T t);

    void createOrUpdate(List<T> t);

    void delete(T T);

    void delete(List<T> t);

    List<T> getByQuery(String q);
}
