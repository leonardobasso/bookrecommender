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
    public static void insertReview(Context ctx) {
        try
        insertReview();
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
    /**
     * Ritorna i dati di tutte le review di un determinato  salvati sul database in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void getReviewByBook(Context ctx)
    {
        try
        insertReview();
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