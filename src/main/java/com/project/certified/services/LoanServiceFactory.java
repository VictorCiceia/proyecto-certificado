package com.project.certified.services;

import com.project.certified.services.Mongo.LoanServiceMongo;
import com.project.certified.services.Postgres.LoanServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoanServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        LoanServiceFactory.applicationContext = context;
    }

    public static LoanService getService() {
        if ("mongo".equals(database)) {
            return applicationContext.getBean(LoanServiceMongo.class);
        } else {
            return applicationContext.getBean(LoanServicePostgres.class);
        }
    }

}
