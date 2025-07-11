package com.bookrecommender.controller;

import io.javalin.http.Context;
import com.bookrecommender.jdbc.BookSQL;
import com.bookrecommender.model.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * Controller per le operazioni sui libri
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.jdbc.BookSQL
 */
public class BookController {

    /**
     * Ritorna i dati di tutti i libri salvati sul database in formato json
     *
     * @param ctx context
     * @author Leonardo Basso
     */
    public static void getAllBooks(Context ctx) {
        List<Book> books = BookSQL.getAllBooks();
        ctx.json(books);
        ctx.status(200);
    }

    public static void getSingleBook(Context ctx) {
        Book book = BookSQL.getSingleBook(ctx.pathParam("id"));
        ctx.json(book);
        ctx.status(200);
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

        Integer yearInt = (year != null) ? Integer.parseInt(year) : null;
        books = BookSQL.searchBook(name, author, yearInt);
        ctx.json(books);
    }

    public void insertReview(Context ctx) {

    }

    public void insertSuggestion(Context ctx) {

    }
}
