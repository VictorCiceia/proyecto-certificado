package com.project.certified.config;

public class DataBase {

    static String database = "postgresql";

    public static final String MONGO = "mongo";
    public static final String POSTGRESQL = "postgresql";

    private static DataBase instance;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public String getDatabase() {
        return database;
    }

}
