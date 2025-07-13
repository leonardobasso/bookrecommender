package com.bookrecommender;

import com.bookrecommender.controller.BookController;
import com.bookrecommender.controller.LibraryController;
import com.bookrecommender.controller.UserController;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

/**
 * Configurazione e router dell'api e di Javalin
 *
 * @author Leonardo Basso
 */
public class Main {
    /**
     * Metodo per configurare Javalin e far partire il server
     *
     * @param args
     * @author Leonard Basso
     */
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        }).start(7070);

        // api/book
        app.get("/api/book/all", BookController::getAllBooks);
        app.get("/api/book/search", BookController::searchBook);
        app.get("/api/book/{id}", BookController::getSingleBook);

        // api/book/suggestion
        app.post("/api/book/suggestion/create", BookController::insertSuggestion);
        app.get("/api/book/suggestion/get/all/{id}", BookController::getLibriConsigliatiLibri);
        app.get("/api/book/suggestion/get/user", BookController::getLibriConsigliatiUtenteLibri);

        // api/user
        app.post("/api/user/register", UserController::register);
        app.post("/api/user/login", UserController::login);

        // api/library
        app.post("/api/library/create", LibraryController::createLibrary);
        app.post("/api/library/add/book", LibraryController::addBook);
        app.get("/api/library/details/{id}", LibraryController::details);
        app.get("/api/library/user/{id}", LibraryController::getLibrariesByUser);
        app.get("/api/library/books-user/{id}", LibraryController::getAllBooksByUser);

        // api/review
    }
}