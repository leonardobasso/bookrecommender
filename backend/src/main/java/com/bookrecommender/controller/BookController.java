package com.bookrecommender.controller;

import io.javalin.http.Context;
import com.bookrecommender.jdbc.BookSQL;
import com.bookrecommender.model.Book;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Controller per le operazioni sui libri
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.jdbc.BookSQL
 */
public class BookController {

    /**
     * Ritorna i dati di tutti i libri salvati sul database in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void getAllBooks(Context ctx) {
        List<Book> books = BookSQL.getAllBooks();
        ctx.json(books);
        ctx.status(200);
    }

    /**
     * Ritorna i dati di tutti i libri consigliati riguardanti un certo libro e un determinato utente in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void getLibriConsigliatiUtenteLibri(Context ctx) {
        List<Book> books = BookSQL.getLibriConsigliatiUtenteLibri();
        ctx.json(books);
        ctx.status(200);
    }

    /**
     * Ritorna i dati di tutti i libri consigliati riguardanti un certo libro e un determinato utente in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void getLibriConsigliatiLibri(Context ctx) {
        List<Book> books = BookSQL.getLibriConsigliatiLibri();
        ctx.json(books);
        ctx.status(200);
    }

    /**
     * Ritorna i dati di un  libro dato un id
     *
     * @param ctx context
     * @author Lorenzo Beretta
     */
    public static void getSingleBook(Context ctx) {
        try {
            Book book = BookSQL.getSingleBook(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", book
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
        }

    }

    /**
     * Controller per la ricerca di libri dato un url di ricerca <i>smth/search/param?</i>
     *
     * @param ctx Context
     * @author Leonardo Basso, Lorenzo Beretta
     */
    public static void searchBook(Context ctx) {
        List<Book> books;
        String name = ctx.queryParam("nome");
        String author = ctx.queryParam("autore");
        String year = ctx.queryParam("annoPub");

        Integer yearInt;
        if (year != null) {
            yearInt = Integer.parseInt(year);
        } else yearInt = null;
        try {
            books = BookSQL.searchBook(name, author, yearInt);
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", books
            ));
        } catch (Exception e) {
            ctx.status(200).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
            throw new RuntimeException(e);
        }

    }

    /**
     * Controller per l'inserimento di un libro tra i libri consigliati
     *
     * @param ctx Context
     * @author Leonardo Basso, Lorenzo Beretta
     */
    public void insertSuggestion(Context ctx) {
        try
        insertLibriConsigliati();
        ctx.status(200).json(Map.of(
                "status", "success",
                "body", books
        ));
    catch(Exception e)
        {
            ctx.status(200).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()

        }

    }
}
