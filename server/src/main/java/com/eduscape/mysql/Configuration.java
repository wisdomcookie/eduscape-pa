package com.eduscape.mysql;

import com.eduscape.Main;

import java.util.HashMap;

public class Configuration {

    private Database database;

    public Configuration(Main instance) {
        reload();
    }

    // Secure? hell no! Functional? yep
    public void reload() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("host", "139.147.9.167"); // Likely needs to point to 127.0.0.1 when running on the server
        map.put("port", 3306);
        map.put("user", "pa_school_admin");
        map.put("password", "88888888");
        map.put("database", "database"); // TODO

        database = new Database(map);
    }

    public Database getDatabase() {
        return database;
    }
}