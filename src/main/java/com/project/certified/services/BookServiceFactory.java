package com.project.certified.services;

import com.project.certified.services.Mongo.BookServiceMongo;
import com.project.certified.services.Postgres.BookServicePostgres;
import org.springframework.beans.factory.annotation.Value;

public class BookServiceFactory {

    @Value("${database.type}")
    static String database = "postgresql";

    public static BookService getBookService() {
        if (database.equals("mongo")) {
            return new BookServiceMongo();
        } else if (database.equals("postgresql")) {
            return new BookServicePostgres();
        }
        return null;
    }

}
