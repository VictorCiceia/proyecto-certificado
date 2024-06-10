package com.project.certified.services;

import com.project.certified.services.Mongo.UserServiceMongo;
import com.project.certified.services.Postgres.UserServicePostgres;
import org.springframework.beans.factory.annotation.Value;

public class UserServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    public static UserService getService() {
        if (database.equals("mongo")) {
            return new UserServiceMongo();
        } else {
            return new UserServicePostgres();
        }
    }

}
