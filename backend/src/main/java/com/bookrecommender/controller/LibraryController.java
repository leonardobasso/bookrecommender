package com.bookrecommender.controller;

import com.bookrecommender.jdbc.LibrarySQL;
import com.bookrecommender.model.Book;
import com.bookrecommender.model.Libreria;
import com.bookrecommender.model.LibroInLibreria;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

/**
 * Controller per la libreria
 *
 * @author Leonardo Basso, Lorenzo Beretta
 * @see LibrarySQL
 * @see com.bookrecommender.model.Libreria
 * @see com.bookrecommender.jdbc.LibrarySQL
 */
public class LibraryController {

    /**
     * Ritorna tutte le librerie di un determinato utente
     *
     * @param ctx Context
     */
    public static void getLibrariesByUser(Context ctx) {
        String id = ctx.pathParam("id");
        List<Libreria> libraries = LibrarySQL.getLibrariesByUser(id);
        try {
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", libraries
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
     * Crea una nuova libreria
     * @param ctx Context
     */
    public static void createLibrary(Context ctx) {
        try {
            Libreria libreria = ctx.bodyAsClass(Libreria.class);
            String name = libreria.getNomeLibreria();
            String userId = libreria.getUserId();
            if (name == null || userId == null) {
                ctx.status(400).json(Map.of(
                        "status", "error",
                        "body", "Nome libreria o userId mancanti"
                ));
                return;
            }
            LibrarySQL.createLibrary(name, userId);
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", ":Creata libreria"
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
     * Aggiunge un libro a una libreria
     *
     * @param ctx Context
     */
    public static void addBook(Context ctx) {
        try {
            LibroInLibreria lil = ctx.bodyAsClass(LibroInLibreria.class);
            int idLibro = lil.getIdLibro();
            int idLibreria = lil.getIdLibreria();

            LibrarySQL.addBook(idLibreria, idLibro);
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", "Libro aggiunto correttamente"
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
     * Mostra tutti i libri di una libreria
     *
     * @param ctx Context
     */
    public static void details(Context ctx) {
        try {
            int idLibreria = Integer.parseInt(ctx.pathParam("id"));
            List<Book> books = LibrarySQL.details(idLibreria);
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
     * RItorna tutti i libri che un utente ha salvato in ogni libreria
     * @param ctx
     */
    public static void getAllBooksByUser(Context ctx) {
        try {
            String userId = ctx.pathParam("id");
            List<Book> books = LibrarySQL.getBooksByUser(userId);
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
}