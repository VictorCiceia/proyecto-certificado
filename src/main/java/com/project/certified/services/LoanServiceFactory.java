package com.project.certified.services;

import com.project.certified.services.Mongo.LoanServiceMongo;
import com.project.certified.services.Postgres.LoanServicePostgres;
import org.springframework.beans.factory.annotation.Value;

public class LoanServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    public static LoanService getService() {
        if (database.equals("mongo")) {
            return new LoanServiceMongo();
        } else {
            return new LoanServicePostgres();
        }
    }

}
