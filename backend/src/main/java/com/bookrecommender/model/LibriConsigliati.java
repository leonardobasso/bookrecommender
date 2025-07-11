package com.bookrecommender.model;

/**
 * Model dei libri consigliati
 */
public class LibriConsigliati {
    private String userId;
    private String libreriaId;
    private String libroConsigliatoId;
    private String libroDeiConsigliId;

    /**
     * Model dei libri consigliati
     * @param userId L'id dell'utente che consiglia il libro
     * @param libreriaId L'id della libreria che vede il libro consigliato
     * @param libroConsigliatoId L'id del libro che viene consigliato
     * @param libroDeiConsigliId L'id del libro che vede consigliare 3 libri
     * @author Leonardo Basso
     */
    public LibriConsigliati(String userId, String libreriaId, String libroConsigliatoId, String libroDeiConsigliId) {
        this.userId = userId;
        this.libreriaId = libreriaId;
        this.libroConsigliatoId = libroConsigliatoId;
        this.libroDeiConsigliId = libroDeiConsigliId;
    }
}
