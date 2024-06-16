package com.project.certified.services;

import com.project.certified.config.DataBase;
import com.project.certified.services.Mongo.BookServiceMongo;
import com.project.certified.services.Postgres.BookServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BookServiceFactory {

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        BookServiceFactory.applicationContext = context;
    }

    public static BookService getService() {
        final DataBase dataBase = DataBase.getInstance();
        if (DataBase.MONGO.equals(dataBase.getDatabase())) {
            return applicationContext.getBean(BookServiceMongo.class);
        } else {
            return applicationContext.getBean(BookServicePostgres.class);
        }
    }

}
