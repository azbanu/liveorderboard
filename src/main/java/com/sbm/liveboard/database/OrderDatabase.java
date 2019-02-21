package com.sbm.liveboard.database;

import com.sbm.liveboard.domain.Order;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// singleton database
public enum OrderDatabase {

    INSTANCE;

    Map<String, Order> database = new ConcurrentHashMap<>();

    public Map<String, Order> getDatabase(){

         return database ;
    }

}
