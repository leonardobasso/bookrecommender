package com.bookrecommender.controller;

import io.javalin.http.Context;
import com.bookrecommender.jdbc.ReviewSQL;
import com.bookrecommender.model.Review;

import java.util.List;
import java.util.Map;

/**
 * Controller per le operazioni sui libri
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Review
 * @see com.bookrecommender.jdbc.ReviewSQL
 */
public class ReviewController {

    /**
     * Ritorna i dati di tutti i libri salvati sul database in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void insertReview(Context ctx) {
        try {
            Review review = ctx.bodyAsClass(Review.class);
            ReviewSQL.insertReview(
                    review.getUserId(),
                    review.getLibroId(),
                    review.getStileVoto(),
                    review.getContenutoVoto(),
                    review.getGradevolezzaVoto(),
                    review.getOriginalitaVoto(),
                    review.getEdizioneVoto(),
                    review.getStileCommento(),
                    review.getContenutoCommento(),
                    review.getGradevolezzaCommento(),
                    review.getOriginalitaCommento(),
                    review.getEdizioneCommento(),
                    review.getVotoFinale(),
                    review.getCommentoFinale()
            );

            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", "Review inserted successfully"
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
        }
    }

    /**
     * Ritorna i dati di tutte le review di un determinato  salvati sul database in formato json
     *
     * @param ctx context
     * @author Lorenzo beretta
     */
    public static void getReviewByBook(Context ctx) {
        try {
            int libroId = Integer.parseInt(ctx.pathParam("libroId"));
            List<Review> reviews = ReviewSQL.getReviewByBook(libroId);
            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", reviews
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
        }
    }
}
