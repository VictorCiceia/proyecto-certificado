package com.project.certified.services;

import com.project.certified.config.DataBase;
import com.project.certified.services.Mongo.LoanServiceMongo;
import com.project.certified.services.Postgres.LoanServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoanServiceFactory {

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        LoanServiceFactory.applicationContext = context;
    }

    public static LoanService getService() {
        final DataBase dataBase = DataBase.getInstance();
        if (DataBase.MONGO.equals(dataBase.getDatabase())) {
            return applicationContext.getBean(LoanServiceMongo.class);
        } else {
            return applicationContext.getBean(LoanServicePostgres.class);
        }
    }

}
