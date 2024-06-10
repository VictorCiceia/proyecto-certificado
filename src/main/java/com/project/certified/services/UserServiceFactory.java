package com.project.certified.services;

import com.project.certified.services.Mongo.UserServiceMongo;
import com.project.certified.services.Postgres.UserServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        UserServiceFactory.applicationContext = context;
    }

    public static UserService getService() {
        if ("mongo".equals(database)) {
            return applicationContext.getBean(UserServiceMongo.class);
        } else {
            return applicationContext.getBean(UserServicePostgres.class);
        }
    }
}
