package com.bookrecommender.controller;

import io.javalin.http.Context;
import com.bookrecommender.jdbc.BookSQL;
import com.bookrecommender.model.Book;

import java.util.List;

/**
 * Controller per le operazioni sui libri
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.jdbc.BookSQL
 * @author Leonardo Basso
 */
public class BookController {

    private BookSQL test = new BookSQL();

    /**
     * Ritorna i dati di tutti i libri salvati sul database in formato json
     * @param ctx context
     * @author Leonardo Basso
     */
    public static void getAllBooks(Context ctx) {
        List<Book> books = BookSQL.getAllBooks();
        ctx.json(books);
    }

    public  static void getSingleBook(Context ctx) {
        Book book = BookSQL.getSingleBook(ctx.pathParam("id"));
        ctx.json(book);
    }

    public void searchBook(Context ctx) {

    }

    public void insertReview(Context ctx) {

    }

    public void insertSuggestion(Context ctx) {

    }
}
