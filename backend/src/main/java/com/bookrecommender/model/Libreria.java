package com.bookrecommender.model;

/**
 * Model della libreria
 */
public class Libreria {
    private String libreriaID;
    private String userID;

    /**
     * Model della libreria
     * @param libreriaID L'id della libreria
     * @param userID L'id dell'utente che ha creato la libreria
     * @author Leonardo Basso
     */
    public Libreria(String libreriaID, String userID) {
        this.libreriaID = libreriaID;
        this.userID = userID;
    }

    public String getLibreriaID() {
        return libreriaID;
    }

    public String getUserID() {
        return userID;
    }
}
