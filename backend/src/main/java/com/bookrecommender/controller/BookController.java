package com.bookrecommender.controller;

import com.bookrecommender.jdbc.LibrarySQL;
import com.bookrecommender.model.LibriConsigliati;
import io.javalin.http.Context;
import com.bookrecommender.jdbc.BookSQL;
import com.bookrecommender.model.Book;

import java.util.List;
import java.util.Map;

/**
 * Controller per le operazioni sui libri
 *
 * @author Lorenzo Beretta, Leonardo Basso
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.jdbc.BookSQL
 */
public class BookController {

    /**
     * Ritorna i dati di tutti i libri salvati sul database in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta,
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
     * @author Lorenzo beretta, Leonardo Basso
     */
    public static void getLibriConsigliatiUtenteLibri(Context ctx) {
        try {
            LibriConsigliati suggestion = ctx.bodyAsClass(LibriConsigliati.class);
            int libroId = suggestion.getLibroDeiConsigliId();
            String userId = suggestion.getUserId();

            List<Book> books = BookSQL.getLibriConsigliatiUtenteLibri(userId, libroId);

            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", books
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna i dati di tutti i libri consigliati riguardanti un certo libro e un determinato utente in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta, Leonardo Basso
     */
    public static void getLibriConsigliatiLibri(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Book> books = BookSQL.getLibriConsigliatiLibri(id);
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", books
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
        }
    }

    /**
     * Ritorna i dati di un libro dato un id
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
    public static void insertSuggestion(Context ctx) {
        try {
            LibriConsigliati suggestion = ctx.bodyAsClass(LibriConsigliati.class);

            int libroConsigliatoId = suggestion.getLibroConsigliatoId();
            int libroDeiConsigliId = suggestion.getLibroDeiConsigliId();
            String userId = suggestion.getUserId();

            String res = BookSQL.insertLibriConsigliati(libroConsigliatoId, libroDeiConsigliId, userId);

            if (res.equals("Già inseriti 3 libri")) {
                ctx.status(412).json(Map.of(
                        "status", "error",
                        "body", res
                ));
            } else if (res.equals("Libro non presente nella libreria")) {
                ctx.status(412).json(Map.of(
                        "status", "error",
                        "body", res
                ));
            }
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", res
            ));
        } catch (Exception e) {
            ctx.status(200).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
        }
    }
}
