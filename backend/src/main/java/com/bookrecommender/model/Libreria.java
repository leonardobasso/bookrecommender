package com.bookrecommender.model;

/**
 * Model della libreria
 *
 * @see com.bookrecommender.jdbc.LibrarySQL
 * @see com.bookrecommender.controller.LibraryController
 */
public class Libreria {
    private String LibreriaId;
    private String nomeLibreria;
    private String userId;

    /**
     * Model della libreria
     *
     * @param libreriaID L'id della libreria
     * @param userID     L'id dell'utente che ha creato la libreria
     * @author Leonardo Basso
     */
    public Libreria(String libreriaID, String nomeLibreria, String userID) {
        this.LibreriaId = libreriaID;
        this.nomeLibreria = nomeLibreria;
        this.userId = userID;
    }

    /**
     * Serve a Jackson
     */
    public Libreria() {
    }

    public String getLibreriaId() {
        return LibreriaId;
    }

    public String getUserId() {
        return userId;
    }

    public String getNomeLibreria() {
        return nomeLibreria;
    }
}
