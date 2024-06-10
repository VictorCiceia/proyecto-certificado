package com.project.certified.services;

import com.project.certified.services.Mongo.BookServiceMongo;
import com.project.certified.services.Postgres.BookServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BookServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        BookServiceFactory.applicationContext = context;
    }

    public static BookService getService() {
        if ("mongo".equals(database)) {
            return applicationContext.getBean(BookServiceMongo.class);
        } else {
            return applicationContext.getBean(BookServicePostgres.class);
        }
    }

}
