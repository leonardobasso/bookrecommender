package com.bookrecommender.model;

/**
 * Model per LibroInLibreria
 * usato per inserire i libri nelle librerie
 *
 * @see com.bookrecommender.jdbc.BookSQL
 * @see com.bookrecommender.controller.BookController
 * @see com.bookrecommender.jdbc.LibrarySQL
 * @see com.bookrecommender.controller.LibraryController
 */
public class LibroInLibreria {
    private int idLibro;
    private int idLibreria;

    public LibroInLibreria(int idLibro, int idLibreria) {
        this.idLibro = idLibro;
        this.idLibreria = idLibreria;
    }

    /**
     * Serve a Jackson
     */
    public LibroInLibreria() {
    }

    public int getIdLibro() {
        return idLibro;
    }

    public int getIdLibreria() {
        return idLibreria;
    }
}
