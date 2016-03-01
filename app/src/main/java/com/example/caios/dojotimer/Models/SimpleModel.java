package com.example.caios.dojotimer.Models;

/**
 * Created by caios on 2/29/16.
 */
public class SimpleModel<T> {

    public Class<T> getClassModel() {
        return (Class<T>) toString().getClass();
    }
}
