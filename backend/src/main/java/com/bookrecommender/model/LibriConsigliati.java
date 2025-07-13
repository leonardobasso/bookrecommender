package com.bookrecommender.model;

/**
 * Model dei libri consigliati
 */
public class LibriConsigliati {
    private String userId;
    private int libroConsigliatoId;
    private int libroDeiConsigliId;

    /**
     * Model dei libri consigliati
     * @param userId L'id dell'utente che consiglia il libro
     * @param libroConsigliatoId L'id del libro che viene consigliato
     * @param libroDeiConsigliId L'id del libro che vede consigliare 3 libri
     * @author Leonardo Basso
     */
    public LibriConsigliati(String userId, int libroConsigliatoId, int libroDeiConsigliId) {
        this.userId = userId;
        this.libroConsigliatoId = libroConsigliatoId;
        this.libroDeiConsigliId = libroDeiConsigliId;
    }

    public LibriConsigliati(){

    }
    public String getUserId() {
        return userId;
    }

    public int getLibroConsigliatoId() {
        return libroConsigliatoId;
    }

    public int getLibroDeiConsigliId() {
        return libroDeiConsigliId;
    }
}
